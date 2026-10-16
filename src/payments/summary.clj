(ns payments.summary)

;; A payment event is a map:
;;
;;   {:payment-id   "payment-1"
;;    :customer-id  "customer-A"
;;    :amount-cents 1000
;;    :status       :pending            ; #{:pending :succeeded :failed}
;;    :occurred-at  #inst-like}         ; a java.time.Instant
;;
;; summarize returns a map of customer-id to summary:
;;
;;   {"customer-A" {:succeeded-amount-cents 1000
;;                  :pending-payment-count  0}}

(defn summarize
  "Given a sequence of payment events, return a map of customer-id to
   {:succeeded-amount-cents n :pending-payment-count n}.

   See PROMPT.md for the rules."
  [events]
  ;; TODO: implement.
  ;; The empty-input test passes against this stub; the worked-example test
  ;; fails until you implement this. That is expected.
  {})
