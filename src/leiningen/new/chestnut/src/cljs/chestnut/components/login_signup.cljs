(ns {{project-ns}}.components.login-signup
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
            [{{project-ns}}.utils.http :as http]))

(defn login-to-facebook [auth-status-channel]
  (js/FB.login
    (fn [facebook-response]
      (js/console.log facebook-response)
      (js/FB.api
        "/me"
        (fn [facebook-profile]
          (js/console.log facebook-profile)
          (http/login
            (.. facebook-response -authResponse -userID)
            (.. facebook-response -authResponse -accessToken)
            (fn [status]
              (put! auth-status-channel status))))))
    #js{"scope" "email"}))

(defn login-signup-view [{:keys [auth-status-channel]} owner]
  (reify
    om/IRenderState
    (render-state [this _]
      (dom/div
        #js {:className "login-panel"}
        (dom/div
          #js {:className "login-component"}
          (dom/button
            #js {:onClick (fn [e] (login-to-facebook auth-status-channel))
                 :className "ui primary button" }
            (dom/i
              #js {:className "facebook icon"})
            "Login or signup with Facebook"))))))
