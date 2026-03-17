# Sorting Algorithms Visualizer

A Java Swing application designed to visualize and benchmark various sorting algorithms. This project provides a real-time graphical representation of how different sorting algorithms operate on arrays of data, alongside a comprehensive benchmarking suite for comparing their performance.

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

<!-- Drag and drop your Bubble Sort video here to replace this comment -->
*Demo video for Bubble Sort*

### Selection Sort
Divides the input list into a sorted and an unsorted sublist, repeatedly selecting the smallest element from the unsorted sublist and moving it to the end of the sorted sublist.
- **Time Complexity**: $O(n^2)$ in all cases.

<!-- Drag and drop your Selection Sort video here to replace this comment -->
*Demo video for Selection Sort*

### Insertion Sort
Builds the final sorted array one item at a time. It is highly efficient for small datasets and partially sorted arrays, though less efficient on large, completely unsorted lists.
- **Time Complexity**: $O(n^2)$ average/worst case, $O(n)$ best case.

<!-- Drag and drop your Insertion Sort video here to replace this comment -->
*Demo video for Insertion Sort*

### Merge Sort
An efficient, stable, divide-and-conquer algorithm that divides the array into halves, sorts them recursively, and then merges the sorted halves back together. Highly reliable for large datasets.
- **Time Complexity**: $O(n \log n)$ in all cases.

<!-- Drag and drop your Merge Sort video here to replace this comment -->
*Demo video for Merge Sort*

### Quick Sort
A highly efficient divide-and-conquer algorithm that partitions the array based on a 'pivot' element, recursively sorting the sub-arrays. It is often the fastest algorithm in practice for many types of data.
- **Time Complexity**: $O(n^2)$ worst case, $O(n \log n)$ average/best case.

<!-- Drag and drop your Quick Sort video here to replace this comment -->
*Demo video for Quick Sort*

### Heap Sort
A comparison-based sorting technique based on a Binary Heap data structure. It operates similarly to selection sort by finding the maximum element and placing it at the end repeatedly.
- **Time Complexity**: $O(n \log n)$ in all cases.

<!-- Drag and drop your Heap Sort video here to replace this comment -->
*Demo video for Heap Sort*
