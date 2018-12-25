import argparse
import random
import reimport time
import numpy as np


def parse_args():
    """
    Parse the arguments and return values of required runtime and random seed.
    """
    parser = argparse.ArgumentParser()
    parser.add_argument("train_data", help="the data file for training", type=argparse.FileType('r'))
    parser.add_argument("test_data", help="the data file for testing", type=argparse.FileType('r'))
    parser.add_argument("-t", "--time_budget", help="specifies the limit of time for this run.", type=int)
    args = parser.parse_args()
    with args.train_data as data:
        lines = data.readlines()
        X, y = parse_line(lines)
    with args.test_data as data:
        lines = data.readlines()
        X1, y1 = parse_line(lines)
    return X, y, X1, y1

def parse_line(lines):
    space_pattern = re.compile(r'\s+')
    matrix = []
    for line in lines:
        line = line.strip(' \t\n')
        line = space_pattern.split(line)
        line_arr = [float(x) for x in line]
        matrix.append(line_arr)
    matrix = np.array(matrix)
    X, y = matrix[:, :-1], matrix[:, -1] 
    return X, y

def main():
    start_time = time.time()
    X, y, X1, y1 = parse_args()
    #svm = SVM(kernel_type='quadratic')
    X, y = X[:400], y[:400]
    svm = SVM()
    svm.fit(X, y)
    #calculate_time = time.time() 
    print("training error:", cal_error(svm.predict(X), y))
    print("test error:", cal_error(svm.predict(X1), y1))
    print("Running Time ", time.time() - start_time)
    print(y.shape[0], y1.shape[0])
    #print("Calculate time:", time.time() - calculate_time)

def cal_error(x1, x2):
    total = x1.shape[0]
    error = 0
    for i in range(0, total):
        if x1[i] != x2[i]:
           error += 1 
    #print(x1, "\t", x1.shape[0])
    return error / total

class SVM:

    def __init__(self, C=1.0, tol=0.001, max_passes=20, kernel_type='linear'):
        self.C = C
        self.tol = tol
        self.max_passes = max_passes
        self.kernels = {'linear': self.linear,
                        'quadratic': self.quadratic}
        self.kernel = self.kernels[kernel_type]                        
        self.alpha = None
        self.b = None
        self.X = None # Train data
        self.y = None # Train data

    def linear(self, x1, x2):
        return np.dot(x1, x2)

    def quadratic(self, x1, x2):
        return np.dot(x1, x2) ** 2

    def fit(self, X, y):
        self.X = X
        self.y = y
        n, d = X.shape
        alpha = np.zeros(n)
        kernel = self.kernel
        b = 0
        passes = 0
        while passes < self.max_passes:
            print(passes)
            num_changed_alphas = 0
            for i in range(0, n):
                x_i, y_i, alpha_i = X[i, :], y[i], alpha[i]
                E_i = self.classifier_formula(alpha, X, y, x_i, b) - y_i 
                if (y_i * E_i < -self.tol and alpha_i < self.C) or\
                    (y_i * E_i > self.tol and alpha_i > 0):
                    j = self.random_select(0, n - 1, i)
                    x_j, y_j, alpha_j = X[j, :], y[j], alpha[j]
                    E_j = self.classifier_formula(alpha, X, y, x_j, b) - y_j
                    alpha_i_old, alpha_j_old = alpha_i, alpha_j
                    L = max(0, alpha_j - alpha_i)
                    H = min(self.C, self.C + alpha_j - alpha_i)
                    if y_j == y_i:
                        L = max(0, alpha_i + alpha_j - self.C)
                        H = min(self.C, alpha_i + alpha_j)
                    if L == H:
                        continue
                    eta = 2 * kernel(x_i, x_j) - kernel(x_i, x_i) - kernel(x_j, x_j)
                    if eta >= 0:
                        continue
                    alpha_j -= y_j * (E_i - E_j) / eta
                    if alpha_j > H:
                        alpha_j = H
                    elif alpha_j < L:
                        alpha_j = L
                    alpha[j] = alpha_j
                    if (abs(alpha_j - alpha_j_old) < 10 ** -5) :
                        continue
                    alpha_i += y_i * y_j * (alpha_j_old - alpha_j)     
                    alpha[i] = alpha_i
                    b1 = b - E_i - y_i * (alpha_i - alpha_i_old) * np.dot(x_i, x_i) - y_j * (alpha_j - alpha_j_old) * \
                        np.dot(x_i, x_j) # maybe needs modifying
                    b2 = b - E_j - y_i * (alpha_i - alpha_i_old) * np.dot(x_i, x_j) - y_j * (alpha_j - alpha_j_old) * \
                        np.dot(x_j, x_j)
                    b = 0
                    if alpha_i > 0 and alpha_i < self.C:
                        b = b1
                    elif alpha_j > 0 and alpha_j < self.C:
                        b = b2
                    else:
                        b = (b1 + b2) / 2
                    num_changed_alphas += 1
                    #print(i, "\t", j, "\t", L,"\t ", H, "\t ", alpha_i, "\t ", alpha_j,"\t ", b)
            if num_changed_alphas == 0:
                passes += 1
        self.alpha = alpha
        self.b = b
        return alpha, b

    def predict(self, X):
        result = [] 
        for x in X:
            test = self.classifier_formula(self.alpha,\
                self.X, self.y, x, self.b)
            if test > 0:
                result.append(1)
            else:
                result.append(-1)
            #result.append(np.sign(self.classifier_formula(self.alpha,\
            #    self.X, self.y, x, self.b).astype(int)))
        return np.array(result)

    def classifier_formula(self, alpha, X, y, x, b):
        coefficient = np.multiply(alpha, y)  
        inner_product = self.kernel(X, x)
        return np.dot(coefficient.transpose(), inner_product) + b

    def random_select(self, a, b, i):
        j = i
        while j == i:
            j = random.randint(a, b)
        return j
    
if __name__ == "__main__":
    main()
