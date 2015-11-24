(ns {{project-ns}}.components.dashboard
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [cljs.core.async :refer [put! chan <!]]
            [{{project-ns}}.utils.auth :as auth]
            [{{project-ns}}.utils.http :as http]))

(defn logout [credentials]
  (auth/clear-credentials)
  (om/update! credentials (auth/get-credentials)))

(defn dashboard-view [{:keys [credentials]} owner]
  (reify
    om/IRenderState
    (render-state [this _]
      (html [:div {:class "dashboard-panel"}
             [:div {:class "dashboard-welcome"}
              [:h1 "Welcome to your dashboard"]]
             [:div {:class "dashboard-content"}
              [:button {:class "ui button"} "A button"]
              [:button {:class "ui button"
                        :on-click #(logout credentials)} "Click here to logout"]]]))))
