(in-ns 'stash.core-test)

(deftest "inc-attr"
  (assert= {:foo 2} (inc-attr {:foo 1} :foo)))

(deftest "get-one"
  (let [saved (save +simple-schmorg+)]
    (assert-that
      (get-one +schmorg+
        [(:pk_uuid +simple-schmorg+) (:pk_integer +simple-schmorg+)]))))

(deftest "reload"
  (let [saved    (save +complete-post+)
        reloaded (reload saved)]
    (assert= saved reloaded)
    (assert-not (identical? saved reloaded))))