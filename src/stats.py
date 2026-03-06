import pandas as pd
import matplotlib.pyplot as plt

# Load data
data = pd.read_csv("results.csv")

# -------------------------
# 1 Runtime vs Size
# -------------------------
plt.figure()

for algo in data["Algorithm"].unique():
    subset = data[data["Algorithm"] == algo].sort_values("Size")
    plt.plot(subset["Size"], subset["Avg Time (ms)"], marker="o", label=algo)

plt.xlabel("Array Size")
plt.ylabel("Average Time (ms)")
plt.title("Sorting Runtime vs Array Size")
plt.legend()
plt.grid(True)

plt.ylim(0, 3000)  # adjust 3000 to whatever max you want to show

plt.savefig("runtime_vs_size.png")
plt.show()


# -------------------------
# 2 Average Runtime (Bar Chart)
# -------------------------
avg_runtime = data.groupby("Algorithm")["Avg Time (ms)"].mean()

plt.figure()
avg_runtime.plot(kind="bar")

plt.title("Average Runtime per Algorithm")
plt.ylabel("Average Time (ms)")
plt.xlabel("Algorithm")


plt.savefig("average_runtime.png")
plt.show()


# -------------------------
# 3 Average Comparisons
# -------------------------
avg_comp = data.groupby("Algorithm")["Comparisons"].mean()

plt.figure()
avg_comp.plot(kind="bar")

plt.title("Average Comparisons per Algorithm")
plt.ylabel("Comparisons")
plt.xlabel("Algorithm")

plt.savefig("average_comparisons.png")
plt.show()


# -------------------------
# 4 Average Interchanges
# -------------------------
avg_inter = data.groupby("Algorithm")["Interchanges"].mean()

plt.figure()
avg_inter.plot(kind="bar")

plt.title("Average Interchanges per Algorithm")
plt.ylabel("Interchanges")
plt.xlabel("Algorithm")

plt.savefig("average_interchanges.png")
plt.show()
