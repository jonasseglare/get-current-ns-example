(ns get-current-ns-example.main
  (:require [get-current-ns-example.core :as nstest]))

(defn ns-str [x]
  (-> x (.getName) name))

(def ^:private my-ns *ns*)

(defn -main [& args]

  
  (println "The actual ns:" (ns-str (nstest/this-ns)))

  (println "\n\nOther examples:")
  (println "The ns according to *ns*:" (ns-str *ns*))
  (println "my-ns:" (ns-str my-ns))
  (println "ns-name: " (nstest/caller-ns-func))
  (println "this-ns3: " (nstest/this-ns3)))
