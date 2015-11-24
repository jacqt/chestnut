(ns {{project-ns}}.components.event
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [cljs.core.async :refer [put! chan <!]]
            [{{project-ns}}.utils.http :as http]))

(defn event-view [{:keys [event]} owner]
  (reify
    om/IRenderState
    (render-state [this _]
      (html [:div {:class "dashboard-panel"}
             [:h1 "A sub page here"]
             [:h2 (str "page id: " (event :id))]]))))
