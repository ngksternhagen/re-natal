 (ns ^:figwheel-no-load env.$PLATFORM$.main
  (:require [reagent.core :as r]
            [re-frame.core :refer [clear-subscription-cache!]]
            [$PROJECT_NAME_HYPHENATED$.$PLATFORM$.core :as core]
            [figwheel.client :as figwheel :include-macros true]))

 (enable-console-print!)

(def cnt (r/atom 0))
(defn reloader [] @cnt [core/app-root])
(def root-el (r/as-element [reloader]))

(defn force-reload! []
  (clear-subscription-cache!)
  (swap! cnt inc))

(figwheel/watch-and-reload
 :websocket-url "ws://$DEV_HOST$:3449/figwheel-ws"
 :heads-up-display false
 :jsload-callback force-reload!)

(core/init)