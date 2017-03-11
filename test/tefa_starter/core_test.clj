(ns tefa-starter.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [tefa-starter.core :refer [describe-location
                                       describe-path
                                       describe-paths
                                       objects-at
                                       describe-objects
                                       location
                                       move]]))

(deftest nodes-test
  (testing "describing locations"
    (let [nodes {:foo "i am here"}]
      (is (= "i am here"
             (describe-location :foo nodes))))))

(deftest edges-test
  (testing "describing path"
    (is (= "there is a road going north from here."
           (describe-path [:north :road]))))
  (testing "describing paths"
    (is (= "there is a road going north from here. there is a road going south from here."
           (describe-paths :foo {:foo {:baz [:north :road]
                                       :bar [:south :road]}})))))

(deftest items-test
  (testing "getting objects at a specific location"
    (let [obj-locs {:a [:foo :floor]
                    :b [:foo :fridge]
                    :c [:bar :floor]}]
      (is (= [:a :b]
             (objects-at :foo obj-locs)))))
  (testing "describing objects at a specific location"
    (let [obj-locs {:a [:foo :floor]
                    :b [:foo :fridge]
                    :c [:bar :floor]}]
      (is (= "there is a on the floor. there is b on the fridge."
             (describe-objects :foo obj-locs))))))

#_(deftest player-actions-test
  (testing "looking around - TODO")
  (testing "moving around"
    (with-redefs [location (atom :foo)]
      (is (= :bar
             (move :bar))))))
