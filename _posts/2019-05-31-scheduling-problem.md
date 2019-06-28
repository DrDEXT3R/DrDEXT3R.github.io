---
title: "Scheduling Problem"
date: 2019-05-31
categories:
    - Java
tags:
    - CLP
header:
    image: "/images/scheduling-problem/museum.jpg"
    teaser: "/images/scheduling-problem/museum-teaser.jpg"
    og_image: "/images/scheduling-problem/museum-teaser.jpg"
excerpt: "Many times we face the problem of scheduling throughout our lives. Sometimes it is quite troublesome and cumbersome. In such situations, you can use CLP, which in this case, significantly shortens the time of finding the final solution and saves many unnecessary nerves."
mathjax: "true"
---

Many times we face the problem of scheduling throughout our lives. Sometimes it is quite troublesome and cumbersome. In such situations, you can use <a href="/java/what-is-clp/" target="_blank">CLP</a>, which in this case, significantly shortens the time of finding the final solution and saves many unnecessary nerves. In order to show how to solve these types of problems, let's present the specific content of the task.

## The Task
The Museum of Art has **four rooms** that exhibit: drawings, paintings, sculptures and photographs. It is open from 10:00 AM to 3:00 PM.  
  
Today the museum awaits four groups of tourists from different countries:
- the Americans are going to arrive at 10:00,
- the Belgians are due at 11:00,
- the Czech are going to show up at 11:30,
- the Danish are going to come at 11:45.  
  
All tourist groups have some more or less favourite types of art, so they have already declared in which order they want to visit the four exhibitions and how much time they want to spend there:  
- the Americans:  
drawings (1&nbsp;h) → paintings (45&nbsp;min) → sculptures (30&nbsp;min) → photographs (15&nbsp;min)  
- the Belgians:  
paintings (15&nbsp;min) → sculptures (1&nbsp;h) → drawings (20&nbsp;min) → photographs (1&nbsp;h)
- the Czech:  
sculptures (20&nbsp;min) → paintings (1&nbsp;h) → drawings (30&nbsp;min) → photographs (45&nbsp;min)  
- the Danish:  
photographs (30&nbsp;min) → drawings (45&nbsp;min) → paintings (10&nbsp;min) → sculptures (1&nbsp;h)  
  
Each group is going to have a guide speaking in their own language, so **no two groups should visit the same room at the same time** (not to disturb each other). However, it is fine if someone has to wait for  a room to become empty, because the museum has got a nice cafe where they can sit in the meantime.  
  
**Is it possible for the visitors to complete their tours in the desired way before the museum is closed?** If yes, what time are they going to visit each of the four exhibitions?

## Let's Write the Program
As always, let's start by creating a special place to store CLP variables and ArrayList to store all sets.
```java
Store store = new Store(); // Store for CLP variables
ArrayList<IntVar> vars = new ArrayList<IntVar>(); // ArrayList of CLP variables to store all sets
```

We’ll also create several IntVar arrays for each nationality. In the loop, for each index of each array, we can define the domain (possible value obtained) and add this element to the ArrayList. The domain is nothing other than the possible time of visiting a given nationality in the museum. Remember that **we have not informed the computer about the museum's working hours so far**. From the point of view of finding solutions, it is not necessary. Let's just assume that 0 min means the opening hour (10:00 AM) and 300 min (3:00 PM) the closing hour of the building.
<div style="text-align: center;">
    <img alt="result" src="/images/scheduling-problem/times.png">
</div>

```java
final int SIZE = 4; // The constant related to the number of exhibitions

IntVar[] americans = new IntVar[SIZE];
IntVar[] belgians = new IntVar[SIZE];
IntVar[] czech = new IntVar[SIZE];
IntVar[] danish = new IntVar[SIZE];

// The maximum duration of nationality in the museum
for (int i = 0; i < SIZE; i++) {
    americans[i] = new IntVar(store, "Americans[" + i + "]", 0, 300);
    belgians[i] = new IntVar(store, "Belgians[" + i + "]", 60, 300);
    czech[i] = new IntVar(store, "Czech[" + i + "]", 90, 300);
    danish[i] = new IntVar(store, "Danish[" + i + "]", 105, 300);
    vars.add(americans[i]);
    vars.add(belgians[i]);
    vars.add(czech[i]);
    vars.add(danish[i]);
}
```

Now is the time to determine how long each nationality will be visiting a given exhibition. The whole will be included in one common array, in which each row will correspond to one exhibition.
<div style="text-align: center;">
    <img alt="result" src="/images/scheduling-problem/durations.png">
</div>

```java
IntVar[] drawings = new IntVar[SIZE];
drawings[0] = new IntVar(store, "durationAmericans", 60, 60);
drawings[1] = new IntVar(store, "durationBelgians", 20, 20);
drawings[2] = new IntVar(store, "durationCzech", 30, 30);
drawings[3] = new IntVar(store, "durationDanish", 45, 45);

IntVar[] paintings = new IntVar[SIZE];
paintings[0] = new IntVar(store, "durationAmericans", 45, 45);
paintings[1] = new IntVar(store, "durationBelgians", 15, 15);
paintings[2] = new IntVar(store, "durationCzech", 60, 60);
paintings[3] = new IntVar(store, "durationDanish", 10, 10);

IntVar[] sculptures = new IntVar[SIZE];
sculptures[0] = new IntVar(store, "durationAmericans", 30, 30);
sculptures[1] = new IntVar(store, "durationBelgians", 60, 60);
sculptures[2] = new IntVar(store, "durationCzech", 20, 20);
sculptures[3] = new IntVar(store, "durationDanish", 60, 60);

IntVar[] photographs = new IntVar[SIZE];
photographs[0] = new IntVar(store, "durationAmericans", 15, 15);
photographs[1] = new IntVar(store, "durationBelgians", 60, 60);
photographs[2] = new IntVar(store, "durationCzech", 45, 45);
photographs[3] = new IntVar(store, "durationDanish", 30, 30);

IntVar[][] durations = new IntVar[SIZE][];
durations[0] = drawings;
durations[1] = paintings;
durations[2] = sculptures;
durations[3] = photographs;
```

## The Cumulative Constraint
The next step will be to ensure that each **exhibition can be visited by only one group at the same time**. For this purpose, we will use a cumulative constraint, which as usual will be forced by the impose method.

> <div style="text-align: justify">Cumulative constraint – introduced to describe problems of tasks scheduling on the limited number of resources. It assumes that in any moment of time the total usage of given resources can’t exceed the given limit.</div>

The constraint takes four parameters:
- List of tasks start times ***Ti***
- List of tasks durations ***Di***
- List of resources required by the task ***Ri***
- Upper limit of the available resources ***Limit***

$$\forall t \in [\min_{1 \leq i \leq n}(T_{i}), \max_{1 \leq i \leq n}(T_{i}+D_{i})]: \sum_{k: T_{k} \leq t \leq T_{k}+D_{k}} R_{k} \leq Limit$$

<span style="text-decoration:underline">Simple example:</span>  
We have 3 tasks on 2 processors. Each task requires one of them.
<div style="text-align: center;">
    <img alt="result" src="/images/scheduling-problem/cumulativeExample.png">
</div>
  
<span style="text-decoration:underline">Exemplary solution:</span>  
<div style="text-align: center;">
    <img alt="result" src="/images/scheduling-problem/chart.png">
</div>

After this short explanation, we can back to our program. The first thing we will do is create a variable named "one". It will help us to create an array of resources (because every nationality needs one exhibition at a time) and non-extendible value - the limit. The "startTimes" array will specify the start times of the tasks, i.e. the domain of times in which exhibitions can be assigned. After imposing cumulative constraint, we repeat it.

```java
IntVar one = new IntVar(store, "one", 1, 1); // Limit
IntVar[] fourOnes = {one, one, one, one}; // List of resources
IntVar[] startTimes = new IntVar[SIZE];

startTimes[0] = americans[0];
startTimes[1] = belgians[0];
startTimes[2] = czech[0];
startTimes[3] = danish[0];
// Exhibition can be visited by only one group at the same time
store.impose(new Cumulative(startTimes, drawings, fourOnes, one)); 

startTimes[0] = americans[1];
startTimes[1] = belgians[1];
startTimes[2] = czech[1];
startTimes[3] = danish[1];
// Exhibition can be visited by only one group at the same time
store.impose(new Cumulative(startTimes, paintings, fourOnes, one)); 

startTimes[0] = americans[2];
startTimes[1] = belgians[2];
startTimes[2] = czech[2];
startTimes[3] = danish[2];
// Exhibition can be visited by only one group at the same time
store.impose(new Cumulative(startTimes, sculptures, fourOnes, one));

startTimes[0] = americans[3];
startTimes[1] = belgians[3];
startTimes[2] = czech[3];
startTimes[3] = danish[3];
// Exhibition can be visited by only one group at the same time
store.impose(new Cumulative(startTimes, photographs, fourOnes, one)); 
```

## Sequence
Now it's time for on the order of visiting exhibitions by each nationality. For this purpose, we will create an array containing the preferences of each of them. We have to choose one sequence according to which we will set it for other nationalities. Let's assume that **the American order will be the reference point** (drawings → paintings → sculptures → photographs). Therefore, belgianPrecedence looks like this {2, 3, 1, 4}.
Next, we will use XplusYlteqZ constraint: 
- as X we will give the time of nationality in the museum;
- as Y time spent at a given exhibition;
- as Z we will also give the time of nationality in the museum.  

The difference between X and Z is that Z represents the next exhibition to be visited. We must make sure that the next exhibition will not start during the previous one.

In order not to exceed the size of the array, for the last iteration our Z will be the opening museum time.

```java
// Setting opening time
IntVar openingTime = new IntVar(store, "openingTime", 0, 300);
vars.add(openingTime);

// Imposing order
int[] americansPrecedence = {1, 2, 3, 4};
for (int i = 0; i < 4; i++) {
    if (i < 3)
        store.impose(new XplusYlteqZ(americans[americansPrecedence[i] - 1],
                durations[americansPrecedence[i] - 1][0],
                americans[americansPrecedence[i + 1] - 1]));
    else
        store.impose(new XplusYlteqZ(americans[americansPrecedence[i] - 1],
                durations[americansPrecedence[i] - 1][0],
                openingTime));
}

int[] belgiansPrecedence = {2, 3, 1, 4};
for (int i = 0; i < 4; i++) {
    if (i < 3)
        store.impose(new XplusYlteqZ(belgians[belgiansPrecedence[i] - 1],
                durations[belgiansPrecedence[i] - 1][1],
                belgians[belgiansPrecedence[i + 1] - 1]));
    else
        store.impose(new XplusYlteqZ(belgians[belgiansPrecedence[i] - 1],
                durations[belgiansPrecedence[i] - 1][1],
                openingTime));
}

int[] czechPrecedence = {3, 2, 1, 4};
for (int i = 0; i < 4; i++) {
    if (i < 3)
        store.impose(new XplusYlteqZ(czech[czechPrecedence[i] - 1],
                durations[czechPrecedence[i] - 1][2],
                czech[czechPrecedence[i + 1] - 1]));
    else
        store.impose(new XplusYlteqZ(czech[czechPrecedence[i] - 1],
                durations[czechPrecedence[i] - 1][2],
                openingTime));
}

int[] danishPrecedence = {4, 1, 2, 3};
for (int i = 0; i < 4; i++) {
    if (i < 3)
        store.impose(new XplusYlteqZ(danish[danishPrecedence[i] - 1],
                durations[danishPrecedence[i] - 1][3],
                danish[danishPrecedence[i + 1] - 1]));
    else
        store.impose(new XplusYlteqZ(danish[danishPrecedence[i] - 1],
                durations[danishPrecedence[i] - 1][3],
                openingTime));
}
```

## Solution
At the end, as always, we are looking for a solution. We will do it exactly the same as in the case of 
<a href="/java/einstein-riddle/" target="_blank">Einstein's Riddle</a>.

```java
// Time - start
long T1 = System.nanoTime();

// Search for solution
Search label = new DepthFirstSearch();
SelectChoicePoint select = new InputOrderSelect(store, vars.toArray(new IntVar[1]), new IndomainMin());
label.labeling(store, select);

// Time - stop
long T2 = System.nanoTime();
System.out.println("Time: " + Long.toString(T2-T1) + "ns");
```
<div style="text-align: center;">
    <img alt="result" src="/images/scheduling-problem/result-console.png">
</div> 
The solution to the problem was returned in the order of our reference point (drawings → paintings → sculptures → photographs). Turning minutes into hours and remembering that the museum is open from 10:00 to 15:00, we get:
<div style="text-align: center;">
    <img alt="result" src="/images/scheduling-problem/result-array.png">
</div> 
In addition, this result can be displayed on the chart using the <a href="http://www.jfree.org/jfreechart/" target="_blank">JFreeChart</a> library:  

<div style="text-align: center;">
    <img alt="result" src="/images/scheduling-problem/result-as-chart.png">
</div> 
**The way of showing the solution is not as important as solving the problem.** The computer did it very quickly. I hope that this example showed once again the good use of *Constraint Logic Programming*. I encourage you to solve it yourself, in case of any ambiguity, the whole code is <a href="https://github.com/DrDEXT3R/DrDEXT3R.github.io/tree/master/programs/Museum" target="_blank">here</a>.