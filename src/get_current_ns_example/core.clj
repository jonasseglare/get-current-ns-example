(ns get-current-ns-example.core
  (:require [clojure.string :as cljstr]))

(defmacro this-ns []
  (let [s (gensym)]
    `(do (def ~s)
         (-> (var ~s)
             (.ns)))))



;; Does not work
(defmacro this-ns2 []
  '*ns*)

(defn caller-ns-func []
  (let [ex                (RuntimeException. "dummy")
        st                (.getStackTrace ex)
        class-names       (mapv #(.getClassName %) st)
        class-name-this   (first class-names)
        class-name-caller (first
                           (drop-while #(= class-name-this %)
                                       class-names))

                                        ; class-name-caller is like "tst.demo.core$funky"
        [ns-name fn-name] (cljstr/split class-name-caller #"\$")]
    {:ns-name ns-name}))

(defmacro this-ns3 []
  `(->> (fn []) str (re-find #"^.*?(?=\$|$)") symbol find-ns))
