(ns petrol-examples.pages.core
  (:require [petrol.core :as petrol]
            [petrol.routing :as petrol-routing]
            [reagent.core :as reagent]
            [petrol-examples.pages.routes :refer [frontend-routes]]
            [petrol-examples.pages.view :as view]))

(def initial-state
  {:view nil})

(defonce !app
  (reagent/atom initial-state))

;; figwheel reload-hook
(defn reload-hook
  []
  (swap! !app identity))

(defn render-fn
  [ui-channel app]
  (reagent/render-component [view/root ui-channel app]
                            js/document.body))

(defn ^:export main
  []
  (enable-console-print!)
  (petrol/start-message-loop! !app
                              render-fn
                              #{(petrol-routing/init frontend-routes)}))
