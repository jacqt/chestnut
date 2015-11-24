(ns {{project-ns}}.models
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [{{project-ns}}.utils.auth :as auth]))

(enable-console-print!)
(defonce app-state (atom {:credentials (auth/get-credentials)
                          :route nil }))
