(ns cljdetector.process.expander
  (:require [clojure.string :as string]
            [cljdetector.storage.storage :as storage]))

;; Common to both solutions
;; ----------------------------------------

(defn sort-instances [instances]
  (sort-by (juxt :fileName :startLine) instances))

(defn merge-clones [cand-a cand-b]
  (let [inst-a (sort-instances (:instances cand-a))
        inst-b (sort-instances (:instances cand-b))]
    {:_id (:_id cand-a)
     :numberOfInstances (:numberOfInstances cand-a)
     :instances (map (fn [ia ib]
                       {:fileName (:fileName ia)
                        :startLine (min (:startLine ia) (:startLine ib))
                        :endLine (max (:endLine ia) (:endLine ib))}
                       ) inst-a inst-b)
     } ))


;; FP Solution
;; ----------------------------------------

(defn overlap-instance? [cand-a cand-b]
  (and (= (:fileName cand-a) (:fileName cand-b))
       (or (and (< (:startLine cand-a) (:startLine cand-b))
                (>= (:endLine cand-a) (:startLine cand-b)))
           (and (< (:startLine cand-b) (:startLine cand-a))
                (>= (:endLine cand-b) (:startLine cand-a))))))

(defn overlaps? [cand-a cand-b]
  (let [inst-a (sort-instances (:instances cand-a))
        inst-b (sort-instances (:instances cand-b))]
    (every? true? (map overlap-instance? inst-a inst-b))))

(defn expand-clones-elegantly [candidates]
  (reduce (fn [clones candidate]
            (let [{overlapping true remaining false} (group-by #(overlaps? candidate %) clones)]
              (if (empty? overlapping)
                  (conj clones candidate)
                  (conj remaining (reduce merge-clones candidate overlapping)))
            )) [] candidates))


;; Aleph-null BoBoTW solution
;; ----------------------------------------

(defn maybe-expand [dbconnection candidate]
  (loop [overlapping (storage/get-overlapping-candidates dbconnection candidate)
         clone candidate]
    (if (empty? overlapping)
      (do
;;        (println "Number of Clones" (storage/count-items "clones") "Remaining candidates" (storage/count-items "candidates"))
        (storage/remove-overlapping-candidates! dbconnection (list candidate))
        clone)
      (let [merged-clone (reduce merge-clones clone overlapping)]
        (storage/remove-overlapping-candidates! dbconnection overlapping)
        (recur (storage/get-overlapping-candidates dbconnection merged-clone)
               merged-clone)))))

(defn expand-clones []
  (let [dbconnection (storage/get-dbconnection)]
    (loop [candidate (storage/get-one-candidate dbconnection)]
      (when candidate
        (storage/store-clone! dbconnection (maybe-expand dbconnection candidate))
        (recur (storage/get-one-candidate dbconnection))))))
