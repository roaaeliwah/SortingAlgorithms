# Sorting Algorithms Visualizer

A Java Swing application designed to visualize and benchmark various sorting algorithms. This project provides a real-time graphical representation of how different sorting algorithms operate on arrays of data, alongside a comprehensive benchmarking suite for comparing their performance.

## Demo Video


https://github.com/user-attachments/assets/0e0d1086-bfe5-4175-b51e-5709780d7ada



## Features

- **Real-Time Visualization**: Watch algorithms sort arrays step-by-step with color-coded operations (e.g., green for sorted, red for comparisons, yellow for swaps).
- **Interactive Controls**: Adjust the animation speed dynamically using a slider to slow down or speed up the visualization.
- **Algorithm Comparison**: Benchmarking tool to compare the execution time of multiple algorithms across different array sizes and initial states (e.g., random, sorted, reversed).
- **Data Export**: Export the benchmarking results and comparison tables directly to CSV files for further analysis.
- **Multiple Algorithms Supported**: Includes implementations for standard sorting strategies. Click on each to see a demo:
  - [Bubble Sort](#bubble-sort)
  - [Selection Sort](#selection-sort)
  - [Insertion Sort](#insertion-sort)
  - [Merge Sort](#merge-sort)
  - [Quick Sort](#quick-sort)
  - [Heap Sort](#heap-sort)

## Technologies Used

- **Java (JDK 23)**: Core application logic.
- **Swing (AWT/Java 2D)**: Graphical User Interface.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 23 or higher installed.
- Maven installed on your system.

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd sortingAlgorithmss
   ```

2. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

3. **Run the main class:**
   You can run the application by executing the `main.Main` class:
   ```bash
   mvn exec:java -Dexec.mainClass="main.Main"
   ```

### Project Structure

- `src/main/java/algorithms/`: Contains the implementations for the various sorting algorithms.
- `src/main/java/gui/`: Holds the UI elements including `SortVisualizer`, `VisualizationPanel`, `ComparisonPanel`, and `MainFrame`.
- `src/main/java/logic/`: Core logic and helper components.
- `src/main/java/main/`: Application entry point (`Main.java`).
- `src/main/java/utils/`: Utilities like `ArrayGenerator`.

## Supported Algorithms

### Bubble Sort
A simple comparison-based algorithm that repeatedly steps through the list, compares adjacent elements, and swaps them if they are in the wrong order. Known for its simplicity but generally inefficient on large datasets.
- **Time Complexity**: $O(n^2)$ average/worst case, $O(n)$ best case.

*Demo video for Bubble Sort*

https://github.com/user-attachments/assets/2c691fc7-fe4a-46e6-9a93-a7f6ccc74830


### Selection Sort
Divides the input list into a sorted and an unsorted sublist, repeatedly selecting the smallest element from the unsorted sublist and moving it to the end of the sorted sublist.
- **Time Complexity**: $O(n^2)$ in all cases.

*Demo video for Selection Sort*

https://github.com/user-attachments/assets/a497d814-f515-4e67-bc82-4d3db49d5838


### Insertion Sort
Builds the final sorted array one item at a time. It is highly efficient for small datasets and partially sorted arrays, though less efficient on large, completely unsorted lists.
- **Time Complexity**: $O(n^2)$ average/worst case, $O(n)$ best case.

*Demo video for Insertion Sort*

https://github.com/user-attachments/assets/a54b5ba8-3f1a-495a-92d5-5c072bf3583a


### Merge Sort
An efficient, stable, divide-and-conquer algorithm that divides the array into halves, sorts them recursively, and then merges the sorted halves back together. Highly reliable for large datasets.
- **Time Complexity**: $O(n \log n)$ in all cases.

*Demo video for Merge Sort*

https://github.com/user-attachments/assets/37fa6797-edd2-4cf0-b408-e8a6c203e42d


### Quick Sort
A highly efficient divide-and-conquer algorithm that partitions the array based on a 'pivot' element, recursively sorting the sub-arrays. It is often the fastest algorithm in practice for many types of data.
- **Time Complexity**: $O(n^2)$ worst case, $O(n \log n)$ average/best case.

*Demo video for Quick Sort*

https://github.com/user-attachments/assets/2817ac25-49e7-4fcb-b42e-e265099ee09f


### Heap Sort
A comparison-based sorting technique based on a Binary Heap data structure. It operates similarly to selection sort by finding the maximum element and placing it at the end repeatedly.
- **Time Complexity**: $O(n \log n)$ in all cases.

*Demo video for Heap Sort*

https://github.com/user-attachments/assets/186e5a7f-b0ef-406e-a960-ac44dde4ff98


