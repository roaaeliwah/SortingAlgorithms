# Sorting Algorithms Lab Report

## 1. Design and Code Structure
The application is structured following object-oriented principles using Java and the Swing library for the user interface. 
The code is divided into several intuitive packages summarizing their responsibilities:
- `algorithms`: Contains all sorting algorithms logic. It is built around an interface `SortingAlgorithm` and an abstract base class `AbstractSortingAlgo` which centralizes common functionalities like step delays, multithreading pauses, comparisons, and interchanges tracking. The specific algorithms (`BubbleSort`, `SelectionSort`, `InsertionSort`, `MergeSort`, `QuickSort` and `HeapSort`) extend this abstract class.
- `gui`: Connects the user to the underlying utilities via `MainFrame`, `VisualizationPanel` (for visually monitoring intermediate sorting operations step by step), and `ComparisonPanel` (for benchmarking various algorithms interactively side-by-side).
- `logic`: Contains core operational connectors like `ComparisonEngine`, orchestrating arrays, execution limits, and packaging runs into `SortResult` data structure records which encompass runtime details.
- `utils`: Contains helper features such as `ArrayGenerator` (for diverse state instantiation), `SizeGenerator`, and visual bar rendering components like `SortVisualizer`.
- `main`: Houses the entry point (`Main`).

## 2. Design Decisions
- **Strategy Pattern for Sorting Engines**: `AbstractSortingAlgo` and the general interface employ a design pattern that renders it effortlessly simple to deploy and inject algorithms via a `SorterFactory` without hard-coding conditions in UI layers.
- **Multithreading for UI/Execution Decoupling**: The architecture correctly relies on multithreaded operations—like `SwingWorker`—internally to perform rigorous execution of benchmarks (`ComparisonPanel`). This ensures that the application prevents standard screen freezes when sorting massive matrices of $N=10,000$. Visualizer updates likewise employ `Thread` blocks cleanly utilizing `Thread.sleep` coupled with EDT interactions.
- **Modular Data Generation Ecosystem**: Classes such as `ArrayGenerator` separate dummy matrix/array creation from evaluation flows. Hence, loading reversed, sorted, or random datasets scales harmoniously.
- **Unified Evaluation Parameters**: All algorithms actively track internal properties globally (`comparisons` & `interchanges`). Consequently, both `VisualizationPanel` (querying live updates dynamically) and `ComparisonPanel` (querying post-execution aggregates) communicate with an identical, dependable API boundary.

## 3. Sorting Comparison output and Analysis
*(Include statistical summaries of multiple runs evaluated through the program. Insert chart comparisons highlighting complexities).*

[PLACEHOLDER: Insert image of the Comparison Table GUI showing varying array sizes output]

[PLACEHOLDER: Insert Plotting charts / Line graph representations demonstrating Execution N-time performance paths]

**Analysis Overview:**
- Modern algorithms such as Quick, Merge, and Heap Sort distinctly affirm optimal $\mathcal{O}(N \log N)$ operational timing across expanding dimensions ($N \approx 5,000$ to $10,000$), markedly outpacing rudimentary ones.
- Between fundamental $\mathcal{O}(N^2)$ designs, **Bubble Sort** reliably reports maximum array interchanges. Conversely, **Selection Sort** executes fixed volumes of comparisons agnostic of initial configurations while intentionally eliminating excessive swap overhead inherently.
- Array generation states (*Sorted vs. Reversed vs. Random*) aggressively redefine real-world measurements; notably, **Insertion Sort** showcases almost linear $\mathcal{O}(N)$ timespan peaks scaling efficiently around structurally pre-sorted inputs compared to full reverse arrays rendering it to $O(N^2)$.
- **Quick Sort** functions tremendously well across random variations but theoretically faces depth vulnerabilities across previously ordered lists, contingent precisely on the pivotal anchor index definitions.

## 4. Sorting Visualization Outputs
The visualization trace mechanism faithfully reflects algorithmic decisions iteratively. The bars correlate functionally back into index amplitudes, transmuting mathematically upon structural modification bounds and halting progressively based on MS tracking pauses integrated into algorithms' interchange routines. 

[PLACEHOLDER: Insert image of GUI Visualizer running Bubble Sort intermediate array bars, including speed settings]

[PLACEHOLDER: Insert static image of Merge / Heap Sort representing dynamic divisions limits visually inside Visualization frame panel]

Upon total array resolution, statistical metrics automatically reveal consolidated metric footprints matching empirical benchmarks accurately below the visualization bounds.
