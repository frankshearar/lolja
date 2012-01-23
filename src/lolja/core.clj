(ns ;^{:doc "Turtle graphics for the browser."
    ;  :author "Frank Shearar"}
  lolja.core)

(defn- change-state [turtle key value]
  (assoc turtle key value :history turtle))

; Questions: do we want switchable coordinates? (cartesion or polar!)
;(defstruct turtle
;  :location
;  :heading
;  :pen-state)

(defn new-turtle []
  {:location '(0 0) :heading 0 :pen-state :pen-down :history nil})

(defn heading [turtle]
  "In what direction is the turtle facing? Measured in degrees, 0 means 'along the x-axis, towards positive infinity'."
  (:heading turtle))

(defn fd [turtle n]
  "Move the turtle forward n units."
  (cond
   (= 0 (heading turtle)) (change-state turtle :location (map #(+ %1 %2) (:location turtle) [n 0]))
   (= 90 (heading turtle)) (change-state turtle :location (map #(+ %1 %2) (:location turtle) [0 n]))))

(defn location [turtle]
  (:location turtle))

(defn bk [turtle n]
  "Move the turtle backwards n units."
  (fd turtle (- n)))

(defn lt [turtle n]
  "Rotate the turtle n degress anticlockwise"
  (change-state turtle :heading (mod (- (heading turtle) n) 360)))

(defn rt [turtle n]
  "Rotate the turtle n degrees clockwise"
  (lt turtle (- n)))

(defn pen-erase [turtle]
  "Erase any lines over which the turtle moves."
  (change-state turtle :pen-state :pen-erase))

(defn pen-down [turtle]
  "Lower the turtle's pen, so that it may draw as it moves."
  (change-state turtle :pen-state :pen-down))

(defn pen-state [turtle]
  "In what state is the turtle's pen? Should be one of:
   :pen-down, :pen-up, :pen-erase"
  (:pen-state turtle))

(defn pen-up [turtle]
  "Lift the turtle's pen up, making it not draw lines during movement."
  (change-state turtle :pen-state :pen-up))

(defn read-command [str]
  (let [toks (.split str " ")]
    (cons
     (keyword (first toks))
     (map #(Integer/valueOf %) (rest toks)))))

(defn command-turtle [command turtle]
  (let [[cmd first-arg & rest] command]
  (cond
   (= :rt cmd) (rt turtle first-arg)
   (= :lt cmd) (lt turtle first-arg)
   (= :fd cmd) (fd turtle first-arg)
   (= :bk cmd) (bk turtle first-arg))))