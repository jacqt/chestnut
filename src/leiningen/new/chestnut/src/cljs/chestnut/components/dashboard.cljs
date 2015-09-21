(ns {{project-ns}}.components.dashboard
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [{{project-ns}}.utils.http :as http]))


(defn dashboard-view [{} owner]
  (reify
    om/IRenderState
    (render-state [this _]
      (dom/div
        #js {:className "dashboard-panel"}
        (dom/div
          #js {:className "dashboard-welcome"}
          (dom/h1
            #js {}
            "Welcome to FriendBnb!")
          )
        (dom/div
          #js {:className ""}
          (dom/button
            #js {:className "ui button"}
            "Click here to do random stuff"))))))
