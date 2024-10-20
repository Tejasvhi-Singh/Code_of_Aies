# ConnectX

## Problem Statement

In ConnectX, players strategize to align a sequence of X tokens either horizontally, vertically, or diagonally on a customizable board of varying dimensions. This project applies reinforcement learning techniques to develop an efficient AI capable of mastering ConnectX. The algorithms developed were tested in a Kaggle competition, where we achieved 9th place out of 225 teams by using AlphaZero as the top-performing strategy.

## Overview

This project explores reinforcement learning algorithms to master ConnectX, a variation of Connect 4. The goal is to align X tokens in a row, column, or diagonal before your opponent. The project utilizes Monte Carlo Tree Search (MCTS), AlphaZero, Minimax, and Greedy algorithms.

### Key Highlights:
- **ConnectX Game Environment**: Strategies are developed for an MxN board, ensuring optimal moves within a 5-second time frame.
- **Reinforcement Learning**: The project uses AlphaZero, MCTS, Minimax, and Greedy algorithms to make intelligent decisions.

## Key Features

1. **ConnectX Game Environment**:
   - Ensures compliance with game rules like avoiding full columns.
   - Implements strategies with a 5-second move time constraint.

2. **Reinforcement Learning Algorithms**:
   - **Greedy Algorithm**: Focuses on making the best immediate decision.
   - **Minimax with Alpha-Beta Pruning**: Evaluates future moves while minimizing the opponent’s score.
   - **Monte Carlo Tree Search (MCTS)**: Simulates potential game moves iteratively and adjusts strategies.
   - **AlphaZero**: Combines MCTS and neural networks to generate optimal moves through self-play.

3. **Neural Networks**:
   - Predicts optimal moves based on the current game state using reinforcement learning and experience replay.

## Methodologies Used

1. **Greedy Algorithm**: Simple strategy that focuses on immediate gains without considering long-term consequences.

2. **Minimax with Alpha-Beta Pruning**: Looks at all possible future moves and outcomes, pruning irrelevant branches for efficiency.

3. **Plain Monte Carlo Tree Search (MCTS)**: Builds a tree of potential game states, simulates moves, and updates based on confidence bounds.

4. **AlphaZero**: Uses self-play to iteratively improve, combining MCTS with a neural network for move probabilities and win/loss predictions.

## Performance Comparison

- **Greedy Algorithm**: Score of 260.
- **Plain MCTS**: Score of 1050.
- **Minimax**: Score of 873.
- **MCTS-Minimax Hybrid**: Score of 980.
- **AlphaZero**: Score of 1282 (Top performer).

## Front-End Mobile App

A front-end Android application was developed using Kotlin to interact with the ConnectX AI. Below are the main functionalities of the app:

- **App Logic**: The app interacts with the reinforcement learning model and allows users to play ConnectX against the AI.
- **Game Interface**: Users can make moves via the app’s user interface, and the AI responds with its move in real-time.

## Project Structure

- **player.py**: Defines the player function, making decisions based on the chosen algorithm.
- **neural_network.py**: Implements the neural network architecture used in AlphaZero.
- **mcts.py**: Runs Monte Carlo Tree Search to explore game trees.
- **train.py**: Scripts for training and fine-tuning the models via self-play.
- **MainActivity.kt**: Android app logic for interacting with the AI and providing a front-end interface.

## Conclusion

- **AlphaZero** performed best in balancing exploration and exploitation, providing optimal strategies through self-improvement.
- MCTS also performed well, but AlphaZero’s integration with neural networks led to superior results.
- Future improvements may involve optimizing neural network parameters and utilizing larger datasets for improved generalization.
