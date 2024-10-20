# ConnectX

## Overview

This project explores the use of reinforcement learning algorithms to master ConnectX, a variation of Connect 4. The objective is to align X tokens in a row, column, or diagonal before your opponent, with varying board dimensions and token counts needed for a win. We experimented with a variety of reinforcement learning techniques, including Monte Carlo Tree Search (MCTS), AlphaZero, Minimax, and Greedy algorithms.

Our project, tested in a Kaggle competition, achieved a **9th place ranking** out of 225 teams by leveraging AlphaZero as the top-performing strategy.

## Key Features

1. **ConnectX Game Environment**:
   - Develops strategies for playing on an MxN board, ensuring optimal moves within a time constraint of 5 seconds per move.
   - Ensures compliance with game rules, such as avoiding selecting full columns.

2. **Reinforcement Learning Algorithms**:
   - **Greedy Algorithm**: Implements a simple yet effective strategy for quick decisions by focusing on immediate gains.
   - **Minimax with Alpha-Beta Pruning**: Evaluates future moves to minimize the opponent’s score while maximizing the player’s own.
   - **Monte Carlo Tree Search (MCTS)**: Iteratively explores and simulates promising moves, adjusting based on simulation results.
   - **AlphaZero**: Combines MCTS and neural networks to generate optimal move probabilities, using self-play for training.

3. **Neural Networks**:
   - Neural networks predict the best moves based on the game state, learning complex strategies through reinforcement learning and experience replay.

## Methodologies Used

1. **Greedy Algorithm**: Focuses on making the best local choice in every step without considering long-term consequences.
   
2. **Minimax with Alpha-Beta Pruning**: Traditional method for perfect information games, examining all possible future moves. Prunes branches that are unlikely to affect the final decision.

3. **Plain Monte Carlo Tree Search (MCTS)**: Builds a tree of potential game states and uses simulations to update the tree and make decisions based on confidence bounds.

4. **AlphaZero**: Combines MCTS with a neural network that predicts move probabilities and value functions, improving through self-play and experience replay.

## Performance Comparison

- **Greedy Algorithm**: Score of 260.
- **Plain MCTS**: Score of 1050.
- **Minimax**: Score of 873.
- **MCTS-Minimax Hybrid**: Score of 980.
- **AlphaZero**: Score of 1282 (Top performer).

## Project Structure

- **player.py**: The core implementation where the player function is defined, making decisions based on the chosen algorithm.
- **neural_network.py**: Implements the neural network architecture used in AlphaZero for decision-making.
- **mcts.py**: Code for running Monte Carlo Tree Search to explore game trees.
- **train.py**: Scripts for training and fine-tuning models using self-play.

## Conclusion

- AlphaZero performed the best in terms of balancing exploration and exploitation, delivering optimal game strategies through self-improvement and learning.
- While MCTS provided strong performance, AlphaZero’s combination with neural networks led to higher success rates, especially when fine-tuning hyperparameters.
- Future improvements could focus on further optimizing neural networks, hyperparameter tuning, and utilizing larger datasets for better generalization.
