# COMP1110 Week 3 Lab

Before you start, you may wish to take a minute to watch the [intro video](https://cs.anu.edu.au/courses/comp1110/labs/mp4/lab3-intro.mp4) that runs through the goals of this lab.

This lab contains material from modules [**O1**](https://cs.anu.edu.au/courses/comp1110/lectures/theme/#O1) (Object Creation) and [**O2**](https://cs.anu.edu.au/courses/comp1110/lectures/theme/#O2) (the `this` keyword).

Remember that *Tasks* are compulsory whilst *Additional Exercises* are extra practice related to this lab and/or the lecture content. Even if you don’t finish the lab, it is recommended that you make use of the additional exercises.

**Tasks**
1. [Bucket](#bucket)
2. [Lab Test Preparation](#prepare-for-the-lab-test)

**Additional Exercises**
1. [Stackable Bucket](#extension-stackable-bucket)

## Before the Lab

* Complete your personal journal for Week 3, commit and push it at least 5 minutes prior to the lab test.
* Watch the [lab demo video](https://cs.anu.edu.au/courses/comp1110/labs/mp4/lab3-demo.mp4) for this lab.
  * Lab 3 demo visualiser [here](https://cscircles.cemc.uwaterloo.ca/java_visualize/#code=public+class+BallsBag+%7B%0A/*%0ADesign+goal%3A%0A1.+owner%0A2.+number%0A3.+add+a+ball/balls%0A4.+drop+a+ball/balls%0A5.+showing+the+ball+object%0A+*/%0A%0A++++private+String+owner%3B%0A++++private+int+number%3B%0A%0A++++BallsBag+(String+name)+%7B%0A++++++++this.owner+%3D+name%3B%0A++++%7D%0A%0A++++void+addBall()+%7B%0A++++++++number%2B%2B%3B%0A++++%7D%0A%0A++++void+addBalls(int+amount)+%7B%0A+++++++number+%2B%3D+amount%3B%0A++++%7D%0A%0A++++void+dropBall()+%7B%0A++++++++number--%3B%0A++++%7D%0A%0A++++void+dropBall(int+amount)+%7B%0A++++++++number+-%3D+amount%3B%0A++++%7D%0A%0A++++%40Override%0A++++public+String+toString()+%7B%0A++++++++return+%22BallsBag%7B%22+%2B%0A++++++++++++++++%22owner%3D'%22+%2B+owner+%2B+'%5C''+%2B%0A++++++++++++++++%22,+number%3D%22+%2B+number+%2B%0A++++++++++++++++'%7D'%3B%0A++++%7D%0A%0A++++public+static+void+main(String%5B%5D+args)+%7B%0A++++++++BallsBag+bagForLeo+%3D+new+BallsBag(%22Leopold%22)%3B%0A%0A++++++++System.out.println(bagForLeo)%3B%0A%0A++++++++bagForLeo.addBall()%3B%0A%0A++++++++System.out.println(bagForLeo)%3B%0A%0A++++++++bagForLeo.addBalls(6)%3B%0A%0A++++++++System.out.println(bagForLeo)%3B%0A%0A++++++++bagForLeo.dropBall()%3B%0A++++++++bagForLeo.dropBall(5)%3B%0A%0A++++++++System.out.println(bagForLeo)%3B%0A++++%7D%0A%7D&mode=edit).
* Next week (Week 4) will be the [lab test](https://cs.anu.edu.au/courses/comp1110/assessments/labtest/).
  You will still need to complete your personal journal for Week 4, commit and push 5 minutes prior to the lab test.

## Purpose

In this lab you will learn more about object-orientation and
develop a very simple object-oriented Java program.

**Your tutor will mark your engagement during the lab.**

## Part I: Check-In

Check in with **your tutor**.  Briefly outline any issues you're having, that you'd particularly like help with during the lab (make sure you have your journal open and refer to it when you meet with your tutor).
When you're not checking in with your tutor, watch the [lab demo video](https://cs.anu.edu.au/courses/comp1110/labs/mp4/lab3-demo.mp4) and start on Part II below.

## Part II: Your Lab Tasks

You are encouraged to discuss these tasks with other people in your lab as well as your tutor. 

### 1. Bucket

Take a look at the short [lab 3 demo video](https://cs.anu.edu.au/courses/comp1110/labs/mp4/lab3-demo.mp4) which steps through an example similar to the one below.

**Create, debug and test the Bucket class.**

This exercise develops the concept of an *object*, which combines *state* (fields) and *behaviour* (methods that operate on those fields).

1.    **Create a new class**

In the Java package `comp1110.lab3`, create a new Java class, `Bucket`.
Each instance of this class represents a bucket that can be filled with water.
When you create a new `Bucket` object, that new object represents a bucket with a particular capacity, `capacity` and certain contents, `contents`.
The units of measurement are not important to this exercise, but you could imagine
that it was litres.

2.    **Write the constructor for the Bucket object**

The constructor for a Bucket should take the capacity as an argument.   The constructor's
signature should thus be `Bucket(double capacity)`.

In your object, both `capacity`
and `contents` should be stored as `doubles`.  Because `capacity` and `contents` are distinct
properties of *each object* (not properties of the class), they need to be
declared as **instance** fields, not *class* fields.
This means you don't use a `static` qualifier when you declare the field.   The `capacity` field will never
change (the capacity of a bucket is something that does not (normally) change), so it
can be declared as `final`.   Both fields should be private to the `Bucket` class.

**Discuss with group**: What would be an example of a class field (in terms of a Bucket class)? Can you think of an instance field of a bucket that would have type `String`?

3.  **Add methods to the Bucket object**

The `Bucket` class should have four public **instance** methods (not *static* methods).

 * The first is` getCapacity()`, which returns a double reflecting the capacity of the bucket.

 * The second is `getContents()`, which returns a double reflecting the current contents of the bucket.

 * The third is `empty()`, which will empty the bucket (its contents will now be zero), and will
 return the contents of the bucket before it was emptied (i.e. how much was emptied from the bucket).

 * The fourth is `add(double amount)`, which will add the given amount to the bucket, and increase
 the contents accordingly, but it will never increase the contents to be more than the capacity
 (i.e. any extra water will be spilled, so the total contents can never be higher than the capacity).
 It doesn't return anything.

**Discuss with group:** What's the difference between a static and instance method?
    Can you think of a static method that might be useful here?
    Is there a difference between a *class* method and a *static* method?

**Discuss with group:** If you run your code here, what are you expecting the output to be? What is the return type for constructors?

4.   **Create instances of type Bucket**

You should create a main method, and in that method, declare two local variables:
`big` and `small`, both of type `Bucket`.   Initialize `big` by
instantiating a new bucket with capacity 10.0.   Initialize `small` by instantiating
a new bucket with capacity 1.0.

5.  **Try out your Bucket methods**

    * Use the `add()` method to add 20.0 to each bucket
    * Print out the bucket contents using `getContents()`.  You should find that both buckets are full to capacity, but not more.
    * Use `empty()` to empty the `big` bucket and  print out the bucket's contents, and ensure that it is in fact empty.
    * Empty the `small` bucket into the `big` bucket.  Print out the contents of each of them and ensure that the `small` bucket is empty
    and the `big` bucket has 1.0 in it.


6.   **Test your program**

Test your work using the provided `L3 BucketTest` class.

Commit all of your work to git, push, close the associated issue (#12), and show your tutor.


### 2. Prepare for the lab test

If you finish early, use any spare time you have to do further preparation
for the [lab test](https://cs.anu.edu.au/courses/comp1110/assessments/labtest/). You are encouraged to ask your tutor
for help in understanding how to complete any of those questions.


## Part III: Additional Exercises 

Remember that these are additional exercises for your own practice. They are not compulsory, but your tutor will be happy to provide assistance and feedback during the lab. 

### 1. Stackable Bucket

**Create a class that inherits from `Bucket`**

Add a new class `StackableBucket` to lab3. This class should inherit from the `Bucket` class.

Add a new instance field `innerBucket` of type `StackableBucket`. (Imagine you want to put a smaller bucket into a larger bucket.) Don't include this as an argument in the constructor; treat it like `contents` in the Bucket class.
Set the default equal to `null`.


Add the constructor `StackableBucket(double capacity,String name)`.
`name` should be a new instance field, capacity should come from the Bucket class.


Add a public instance method `getInnerBucket()` that will return name of the innerBucket.

Add a new public instance method `setInnerBucket(StackableBucket smallerBucket)` which should:
* Print `Too large to stack!` if the `smallerBucket` has a larger capacity than the bucket.
* Set the `smallerBucket` as the innerBucket of your bucket, if your bucket has a larger capacity **and** does not curently have a innerBucket.
* If the bucket already has an innerBucket, the `smallerBucket` should try to stack into the `innerBucket`.

Try in main method:

Initialize 3 StackableBuckets- `big` with capacity `10.0`, `small` with capacity `1.0` and `medium` with capacity `5.0`. The names should be big, small and medium.
* Use `setInnerBucket()` to stack the `big` bucket into the `medium` bucket. If you run this, your code should print `Too large to stack!`.
* Use `setInnerBucket()` to stack the `medium` bucket into the `big` bucket. Print the `getInnerBucket()` of the `big` bucket. This should return `medium`
* Use `setInnerBucket()` to stack the `small` bucket into the `big` bucket. Print `getInnerBucket()` of the `big` and `medium` buckets. The small bucket should be stacked in the medium bucket, which is stacked in the big bucket.


Add a new public instance method `unstackBuckets()`. This method should remove all buckets from inside the bucket, unstack them from each other.
If you run this method on the `big` bucket, the big and medium buckets should now have no innerBucket.

