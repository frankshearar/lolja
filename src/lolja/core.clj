(ns ;^{:doc "Turtle graphics for the browser."
    ;  :author "Frank Shearar"}
  lolja.core)


; Questions: do we want switchable coordinates? (cartesion or polar!)
;(defstruct turtle
;  :location
;  :heading
;  :pen-state)

(defn read-command [str]
  (let [toks (.split str " ")]
    (cons
     (keyword (first toks))
     (map #(Integer/valueOf %) (rest toks)))))

(defn new-turtle []
  {:location '(0 0) :heading 0 :pen-state :pen-down})

(defn fd [turtle n]
  "Move the turtle forward n units."
  turtle)

(defn pen-erase [turtle]
  "Erase any lines over which the turtle moves."
  (assoc turtle :pen-state :pen-erase))

(defn pen-down [turtle]
  "Lower the turtle's pen, so that it may draw as it moves."
  (assoc turtle :pen-state :pen-down))

(defn pen-state [turtle]
  "In what state is the turtle's pen? Should be one of:
   :pen-down, :pen-up, :pen-erase"
  (:pen-state turtle))

(defn pen-up [turtle]
  "Lift the turtle's pen up, making it not draw lines during movement."
  (assoc turtle :pen-state :pen-up))