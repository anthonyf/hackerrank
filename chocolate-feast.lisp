(eval-when (:compile-toplevel :load-toplevel :execute)
  (defpackage #:chocolate-feast
    (:use :cl)))
(in-package :chocolate-feast)

(defun purchase (n c m)
  (let* ((candybars (floor (/ n c)))
         (wrappers candybars))
    (loop while (>= wrappers m)
       do (let ((redeemed (floor (/ wrappers m))))
            (incf candybars redeemed)
            (setf wrappers (+ redeemed (mod wrappers m)))))
    candybars))

(defun solve ()
  (let ((number-of-test-cases (parse-integer (read-line))))
    (loop repeat number-of-test-cases
       do (let* ((n (read))
                 (c (read))
                 (m (read)))
            (princ (purchase n c m)) (terpri)))))

(defun solve-sample ()
  (with-input-from-string (*standard-input* "3
10 2 5
12 4 4
6 2 2")
    (solve)))

(solve)
