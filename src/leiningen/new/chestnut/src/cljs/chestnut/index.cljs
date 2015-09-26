(ns {{project-ns}}.index
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljsjs.jquery]
            [cljs.core.async :refer [put! chan <!]]
            [{{project-ns}}.components.dashboard :as dashboard]
            [{{project-ns}}.components.event :as event]
            [{{project-ns}}.components.landing-page :as landing-page]
            [{{project-ns}}.utils.auth :as auth]))

(defn index-logged-in-view [state owner]
  (reify
    om/IRenderState
    (render-state [this _]
      (case (@state :route)
        "home" (om/build dashboard/dashboard-view state)
        "event" (om/build event/event-view state)
        (comment default) (om/build dashboard/dashboard-view state)))))

(defn index-view [state owner]
  (reify
    om/IRenderState
    (render-state [this _]
      (if (= (@state :credentials) nil)
        (om/build landing-page/landing-page-view state)
        (om/build index-logged-in-view state)))))
