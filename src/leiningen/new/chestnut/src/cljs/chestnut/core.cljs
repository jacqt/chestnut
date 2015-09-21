(ns {{project-ns}}.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            {{{core-cljs-requires}}}
            [om.dom :as dom :include-macros true]
            [cljsjs.jquery]
            [secretary.core :as secretary :include-macros true :refer-macros [defroute]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [cljs.core.async :refer [put! chan <!]]
            [{{project-ns}}.components.dashboard :as dashboard]
            [{{project-ns}}.components.login-signup :as login-signup]
            [{{project-ns}}.utils.auth :as auth])
  (:import goog.History))

(enable-console-print!)

; enable fallback that don't have HTML 5 History
(secretary/set-config! :prefix "#")

(def auth-status-channel (chan))

(def app-state (atom {:text "Hello Chestnut!",
                      :auth-status-channel auth-status-channel}))
(defn load-login-info []
  (let [credentials (auth/get-credentials)]
    (if
      (not (= credentials nil))
      (-> js/window
        .-location
        (set! "#/dashboard"))
      (js/alert "YOU ARE NOT LOGGED IN"))))

;; on app-state channels for state updates
(defn listen-for-changes []
  (go
    (loop []
      (.log js/console (<! auth-status-channel))
      (load-login-info)
      (recur))))

(defroute
  "/" []
  (om/root
    login-signup/login-signup-view
    app-state
    {:target (. js/document (getElementById "app"))}))

(defroute
  "/dashboard" []
  (om/root
    dashboard/dashboard-view
    app-state
    {:target (. js/document (getElementById "app"))}))

;; Quick and dirty history configuration.
(let [h (History.)]
  (goog.events/listen h EventType/NAVIGATE #(secretary/dispatch! (.-token %)))
  (doto h (.setEnabled true)))

(defn main []
  (load-login-info)
  (listen-for-changes))

(defn on-js-reload []
  (js/console.log "loading js again")
  (secretary/dispatch!
    (.substring (.. js/window -location -hash) 1)))
