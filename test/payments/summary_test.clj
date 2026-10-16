(ns payments.summary-test
  (:require [clojure.test :refer [deftest is testing]]
            [payments.summary :as summary])
  (:import [java.time Instant]))

(defn- event [payment-id customer-id amount-cents status iso-instant]
  {:payment-id   payment-id
   :customer-id  customer-id
   :amount-cents amount-cents
   :status       status
   :occurred-at  (Instant/parse iso-instant)})

(deftest empty-input-produces-empty-result
  (is (empty? (summary/summarize []))))

(deftest worked-example
  (testing "events supplied out of chronological order on purpose"
    (let [events [(event "payment-3" "customer-B" 5000 :succeeded "2026-01-01T10:03:00Z")
                  (event "payment-1" "customer-A" 1000 :pending   "2026-01-01T10:00:00Z")
                  (event "payment-2" "customer-A" 2500 :failed    "2026-01-01T10:01:00Z")
                  (event "payment-1" "customer-A" 1000 :succeeded "2026-01-01T10:02:00Z")]
          result (summary/summarize events)]
      (is (= 2 (count result)))
      (is (= {:succeeded-amount-cents 1000 :pending-payment-count 0}
             (get result "customer-A")))
      (is (= {:succeeded-amount-cents 5000 :pending-payment-count 0}
             (get result "customer-B"))))))
