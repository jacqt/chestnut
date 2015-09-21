(ns {{project-ns}}.main
  (:require [{{project-ns}}.core :as core]
            [figwheel.client :as figwheel :include-macros true]
            [cljs.core.async :refer [put!]]
            [weasel.repl :as weasel]))

(figwheel/watch-and-reload
  :websocket-url "ws://localhost:3449/figwheel-ws"
  :jsload-callback (fn []
                     (core/main)
                     (core/on-js-reload)))

(weasel/connect "ws://localhost:9001" :verbose true :print #{:repl :console})

(core/main)
