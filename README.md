# Doctor Review System

Welcome to the **Doctor Review System** – a JavaFX-based desktop application that allows users to search, sort, and review doctors. Created as part of a Year 1 group project module, this project focuses on user and admin interactions within a doctor review platform.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
- [Known Issues](#known-issues)
- [Contributions](#contributions)

## Project Overview

The Doctor Review System was developed as part of a group project during my first year in Computer Science. Each group member was assigned a specific component, and I independently designed and implemented this doctor review component. Despite being new to Java, I built the system from scratch, gaining valuable experience in JavaFX and SQLite database integration.

This application allows users to register, log in, and access various features based on their role (user or admin). Users can view, sort, and search for doctors, as well as submit reviews and star ratings. The system includes an admin role for handling user feedback, bug reports, and improvement suggestions.

## Features

### User Features
- **User Registration and Login**: New users can register, while existing users can log in to access the application.
- **Doctor Search and Sorting**:
    - Search for doctors by name.
    - Sort doctors alphabetically or by rating (ascending or descending).
    - Filter doctors by specialization (e.g., Neurology, Cardiology).
- **Doctor Reviews**:
    - View feedback from other users.
    - Submit reviews and rate doctors on a 1-5 star scale.
- **Feedback to Admin**:
    - Send messages to the admin for general information.
    - Report bugs.
    - Suggest improvements.

### Admin Features
- **Admin Dashboard**:
    - View and manage user feedback.
    - Receive and respond to bug reports and suggestions.

## Technologies Used

- **JavaFX**: Used for building the user interface.
- **SQLite**: Local database for storing user data, doctor profiles, reviews, and messages.

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/otuemre/doctorReviewSystem.git
   ```
2. **Open in an IDE**: Open the project in an IDE such as IntelliJ IDEA or Eclipse, which supports JavaFX.
3. **Set Up Database**: The SQLite database file (`doctorReview.db`) is already included in the project. Ensure you have SQLite libraries available in your environment.
4. **Run the Project**: Compile and run the project in your IDE to launch the application.

## Known Issues

- **Database Locking**: Occasionally, the SQLite database may lock during certain operations, causing temporary access issues. This issue is due to the limitations of using a file-based database and will be addressed in future improvements.
- **Minor Bugs**: As my first Java project, some minor bugs may still be present. Feedback and contributions are welcome!

## Contributions

This project was a group effort, with each team member responsible for a distinct component. The Doctor Review System component was designed and implemented independently by me, Emre. I handled all aspects of this component, from the database structure and functionality to the user interface design and role-based access control.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
