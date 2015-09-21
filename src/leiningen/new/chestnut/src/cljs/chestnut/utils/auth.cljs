(ns {{project-ns}}.utils.auth
  (:require [goog.net.cookies :as cookies]))

(defn set-credentials [{:keys [facebook-id auth-token]}]
  (println "Trying to set credential cookies now...")
  (.set goog.net.cookies "facebook-id" facebook-id -1)
  (.set goog.net.cookies "auth-token" auth-token -1))

(defn clear-credentials []
  (println "Clearing all cookies now...")
  (.clear goog.net.cookies))

(defn get-credentials []
  (println "Retrieving credentials from cookies")
  {:facebook-id (.get goog.net.cookies "facebook-id")
   :auth-token (.get goog.net.cookies "auth-token")})
