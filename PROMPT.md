# Senior Engineer Technical Exercise: Payment Summary

You are working in a small Clojure project with tests already configured.

This exercise has two parts. You have the first part now; the second part will be
given to you partway through the session.

We are evaluating your reasoning and engineering decisions, not your recall of
exact function names. Please explain your thinking as you work. You can ask
requirement questions freely; when several implementation approaches are
reasonable, we will usually ask you to pick one and explain why.

---

## Part 1: Implement a payment summary

The system receives status events for payment attempts. An event is a map:

```clojure
{:payment-id   "payment-1"
 :customer-id  "customer-A"
 :amount-cents 1000
 :status       :pending      ; one of :pending :succeeded :failed
 :occurred-at  inst}         ; a java.time.Instant
```

Implement `payments.summary/summarize`. It takes a sequence of events and
returns a map of customer id to summary:

```clojure
{"customer-A" {:succeeded-amount-cents 1000
               :pending-payment-count  0}}
```

Rules:
- Events may arrive in any order.
- A payment may have multiple events.
- The event with the latest `:occurred-at` represents the current state of that payment.
- A `:succeeded` payment contributes its amount to the customer's `:succeeded-amount-cents`.
- A `:pending` payment contributes one to the customer's `:pending-payment-count`.
- A `:failed` payment contributes neither.
- Each payment is expected to keep the same `:customer-id` and `:amount-cents` across its events.
- Amounts are non-negative. Required keys are present.
- Empty input produces an empty result.
- Result ordering does not matter.

Add whatever tests you consider important.

### Example
Given these events in any order:
```
payment-1, customer-A, 1000, :pending,   10:00
payment-2, customer-A, 2500, :failed,    10:01
payment-1, customer-A, 1000, :succeeded, 10:02
payment-3, customer-B, 5000, :succeeded, 10:03
```
The result should be:
```clojure
{"customer-A" {:succeeded-amount-cents 1000 :pending-payment-count 0}
 "customer-B" {:succeeded-amount-cents 5000 :pending-payment-count 0}}
```
