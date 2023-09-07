# Projectes de ED desde 18/02/2022 (DD/MM/AAAA)
## Pràctica 1: DLL, Sorted DLL, Hash Table 
### DLL
DLL stands for Doubly Linked List, it is a special type of linked list where each element has a reference to the previous and the next element on the list.
The implementation allows to store a generic element T. Each element will be added to the end of the list. 
### Sorted DLL
As the name implies this Doubly Linked List is sorted through the Comparable<T> class. It will be sorted and the added element will be placed in the correct possition.
There is also a toggle that allows to order the list from smallest to largest or from largest to smallest.
### Hash Table
A simple implementation of a Hash Table that gets bigger when collisions excede a certain limit.
## Pràctica 2: Grafs
The objective of this assignment was to take a data base ("icaen.json") that stores information of charging stations, parse it, and connect them if they are no longer than 40km apart. That way a Graph is created, on: 
- Each edge stores a reference to the nodes it is connected and the distance of that edge.
- Each node stores the information of that charging station (latitude, longitude, power, aviability, name...) and the nodes it is connected to.
  
There's a method that between two charging stations it shows the optimal way to get there (minimal distance, where will you charge up). The algorithm will account for the autonomy of the vehicle throwing an exception if the location is inaccessible. Here's 2 examples:
> Mind Maps has every route mapped and, icaen.json only some locations connecting roads (edges) if they are less than 40km apart.

| Maps  | Algorithm  |
| :---  | ---:  |
| ![image](https://github.com/Espurna01/PractiquesED/assets/69860562/56d1434a-78dc-4f53-a957-6e2446eeb48a) | ![image](https://github.com/Espurna01/PractiquesED/assets/69860562/c16c9033-8a95-4e26-a1dd-d2fe2b6946f4) |
| ![image](https://github.com/Espurna01/PractiquesED/assets/69860562/f1e3f8c9-7236-4ee5-9a4e-57575670a52d) | ![image](https://github.com/Espurna01/PractiquesED/assets/69860562/79db5325-3c1c-4dde-a5f4-5641649a5a38) |



