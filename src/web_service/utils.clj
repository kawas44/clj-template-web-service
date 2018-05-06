(ns web-service.utils)


(defn deep-merge* [& maps]
  (let [f (fn [old new]
            (if (and (map? old) (map? new))
              (merge-with deep-merge* old new)
              new))]
    (if (every? map? maps)
      (apply merge-with f maps)
      (last maps))))

(defn deep-merge
  "Merge nested maps. At each level maps are merged left to right. When all
  maps have a common key whose value is also a map, those maps are merged
  recursively. If any of the values are not a map then the value from the
  right-most map is chosen.

  https://github.com/circleci/frontend/blob/c189f3546afe49b64c8ee86d92ff67ed9d2eda78/src-cljs/frontend/utils.cljs#L238

  E.g.:
  user=> (deep-merge {:a {:b 1}} {:a {:c 3}})
  {:a {:c 3, :b 1}}

  user=> (deep-merge {:a {:b 1}} {:a {:b 2}})
  {:a {:b 2}}

  user=> (deep-merge {:a {:b 1}} {:a {:b {:c 4}}})
  {:a {:b {:c 4}}}

  user=> (deep-merge {:a {:b {:c 1}}} {:a {:b {:e 2 :c 15} :f 3}})
  {:a {:f 3, :b {:e 2, :c 15}}}

  Each of the arguments to this fn must be maps:

  user=> (deep-merge {:a 1} [1 2])
  AssertionError Assert failed: (and (map? m) (every? map? ms))

  Like merge, a key that maps to nil will override the same key in an earlier
  map that maps to a non-nil value:

  user=> (deep-merge {:a {:b {:c 1}, :d {:e 2}}}
                     {:a {:b nil, :d {:f 3}}})
  {:a {:b nil, :d {:f 3, :e 2}}}"
  [& maps]
  (let [maps (filter identity maps)]
    (assert (every? map? maps))
    (apply merge-with deep-merge* maps)))
