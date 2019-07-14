---
title: "CLP #4: Optimization Problem"
date: 2019-06-30
categories:
    - Java
tags:
    - CLP
header:
    image: "/assets/images/blog/optimization-problem/optimization.jpg"
    teaser: "/assets/images/blog/optimization-problem/optimization-teaser.jpg"
    og_image: "/assets/images/blog/optimization-problem/optimization-teaser.jpg"
excerpt: "Optimization plays a very important role in our lives. I think none of us likes to waste time unnecessarily. That's why we think so often how to sort tasks and activities during the day. And if so, try to use CLP?"
---

Optimization plays a very important role in our lives. I think none of us likes to waste time unnecessarily. That's why we think so often how to sort tasks and activities during the day. And if so, try to use <a href="/java/what-is-clp/" target="_blank">CLP</a>? Since we used it for the <a href="/java/scheduling-problem/" target="_blank">scheduling problem</a> or solving the <a href="/java/einstein-riddle/" target="_blank">Einstein's Riddle</a>, we will deal with optimization now.

## The Task
Assigning colors to regions of a map in such a way that no adjacent regions are assigned the same color, **while minimizing the number of used colors**.
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/optimization-problem/map-template.png">
</div>

## Lines of Code
We will start by creating a special place to store CLP variables and int value to store number of all regions (A-F). Note that we do not create ArrayList (dynamically sized collection of elements). We did the same in the first post of this series (<a href="/java/what-is-clp/" target="_blank">CLP #1</a>). This example is not as complicated as the previous two, therefore IntVar array is enough for this purpose. We will store the domain of each region in it.

```java
// Store for CLP variables
Store store = new Store();

// The variable for storing number of regions
int noOfRegions = 6;

// The array for storing the domain of each region
IntVar[] regions = new IntVar[noOfRegions];
for (int i=0; i<noOfRegions; i++) {
    regions[i] = new IntVar(store, "regions"+i, 1, noOfRegions);
}
```

Now we need to inform the computer about all regions that can not have the same color. One way is to specify groups in which each region is adjacent to another in group. In our case it will be:
- *A*, *B*, *C*, *D*;
- *A*, *D*, *F*;
- *D*, *E*, *F*. 


<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/optimization-problem/groups.png">
</div>

For this purpose, we will store the entire group in the IntVar array, which we then give as the **Alldifferent()** parameter. This type of constraint enforces assignment of different values to all variables in the given list. It removes assigned value from the domains of other variables. Remember that our array is IntVar type, that's why we have to store such elements in it. Thus **regions[0] will represent A, regions[1] → B, ..., regions[5] → F**.

```java
// Neighboring regions (A, B, C, D)
IntVar[] group1 = {regions[0], regions[1], regions[2], regions[3]};
store.impose(new Alldifferent(group1));

// Neighboring regions (A, D, F)
IntVar[] group2 = {regions[0], regions[3], regions[5]};
store.impose(new Alldifferent(group2));

// Neighboring regions (D, E, F)
IntVar[] group3 = {regions[3], regions[4], regions[5]};
store.impose(new Alldifferent(group3));
```

The second way, to inform the computer about all regions that can not have the same color, is to put all neighboring regions:
*(A,&nbsp;B)*, *(A,&nbsp;C)*, *(A,&nbsp;D)*, *(A,&nbsp;F)*, *(B,&nbsp;C)*, *(B,&nbsp;D)*, *(C,&nbsp;D)*, *(C,&nbsp;E)*, *(D,&nbsp;E)*, *(D,&nbsp;F)*, *(E,&nbsp;F)*. The rest is analogous to the description above.


## Search Solution
The last thing we need to do is look for a solution. There is no big surprise here, we will do it like the previous times. Let's remember about the lack of ArrayList, that's why **the second parameter in InputOrderSelect() will be just *regions***.

```java
// Time - start
long T1 = System.nanoTime();

// Search for solution
Search label = new DepthFirstSearch();
SelectChoicePoint select = new InputOrderSelect(store, regions, new IndomainMin());
label.labeling(store, select);

// Time - stop
long T2 = System.nanoTime();
System.out.println("Time: " + Long.toString(T2-T1) + "ns");
```
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/optimization-problem/result.png">
</div>

As you can see, **4 colors were used**. For a better illustration, let's assume that 1 is blue, 2 → green, 3 → red, 4 → yellow.

<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/optimization-problem/map-solved.png">
</div>

No two neighboring regions have the same color, i.e. **the goal has been achieved**.

## Homework
This type of task has been limited to a specific example - the map shown at the beginning of the article. **Nothing prevents you to devise with such a map yourself**. The only limit is your creativity. If there are no ideas, I prepared two more cases for those interested.
<div style="text-align: center;">
    <img alt="result" src="/assets/images/blog/optimization-problem/examples.png">
</div>

## Summary
This is how the next use of *Constraint Logic Programming* is shown. I encourage you to **solve this problem yourself**, and if necessary, you can see the entire program code <a href="https://github.com/DrDEXT3R/DrDEXT3R.github.io/tree/master/assets/programs/MapColoring" target="_blank">here</a>. As a curiosity, I will also say that **for each devised map, the maximum number of colors is 4**. This is due to the *"Four Color Theorem"*, about which you can read more on the <a href="https://en.wikipedia.org/wiki/Four_color_theorem" target="_blank">Internet</a>.