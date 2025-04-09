# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## [2025.01.30](https://github.com/C-Zong/portfolio-2231/releases/tag/v2025.01.30)

### Added Component Brainstorm

- Designed a `ToDoList` component
- Designed a `TaskTracker` component
- Designed a `TaskManagement` component

## [2025.02.13](https://github.com/C-Zong/portfolio-2231/releases/tag/v2025.02.13)

### Updated Component Brainstorm

- Designed a `Library` component
- Designed a `BudgetTracker` component

## [2025.02.17](https://github.com/C-Zong/portfolio-2231/releases/tag/v2025.02.17)

### Added Component Proof

- Designed a proof of concept for `BudgetTracker` component

## [2025.03.03](https://github.com/C-Zong/portfolio-2231/releases/tag/v2025.03.03)

### Added Component Interfaces

- Designed kernel and enhanced interfaces for `BudgetTracker` component

### Improvements Compared to Component Proof

- Refer to components.queue.QueueKernel, and write a better header for the kernel interface.
- Improve method headers by adding details like @updates and @ensures.
- Not specify that the `BudgetTracker` lies on the stack.

## [2025.03.25](https://github.com/C-Zong/portfolio-2231/releases/tag/v2025.03.25)

### Added Component Abstract Class

- Designed abstract class for `BudgetTracker` component

### Updated Kernel Methods

- Added a constructor and public methods to the inner class `Record`
- Removed an unused parameter from the `printNumOfRecords` method
- Updated the required clause of the `addToIndex` method

## [2025.04.01](https://github.com/C-Zong/portfolio-2231/releases/tag/v2025.04.01)

### Updated Abstract Class and Kernel Class with Instructor's Guidance

- Removed the constructor and fields from the abstract class
- Added getter and setter for `accountName` in `BudgetKernel`
- Renamed `Record` to `Transaction`
- Refactored `Transaction` to a record for immutable data storage
