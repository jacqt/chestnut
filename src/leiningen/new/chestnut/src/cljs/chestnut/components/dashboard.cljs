(ns {{project-ns}}.components.dashboard
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [{{project-ns}}.utils.auth :as auth]  
            [{{project-ns}}.utils.http :as http]))


(defn logout [auth-changed-channel]
  (auth/clear-credentials)
  (put! auth-changed-channel "logged-out"))

(defn dashboard-view [{:keys [auth-changed-channel]} owner]
  (reify
    om/IRenderState
    (render-state [this _]
      (dom/div
        #js {:className "dashboard-panel"}
        (dom/div
          #js {:className "dashboard-welcome"}
          (dom/h1
            #js {}
            "Welcome to FriendBnb!"))
        (dom/div
          #js {:className "dashboard-content"}
          (dom/button
            #js {:className "ui button"}
            "Click here to do random stuff")
          (dom/button
            #js {:className "ui button"
                 :onClick (fn [e] (logout auth-changed-channel))}
            "Click here to logout")
          (dom/input
            #js {:className "dashboard-input"
                 :placeholder "Enter your friend's name!"}))))))
