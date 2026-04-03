# IMDB Movie Database - Client-Server Application

A networked movie database management system built with Java, JavaFX, and TCP sockets. Production companies log in to view, add, search, sort, and transfer movies between companies in real time. The application follows a client-server architecture where multiple clients connect simultaneously to a central server that manages the shared movie database.

## Features

### Movie Management
- **View movies** in a tabular format with title, release year, genre, running time, budget, revenue, and profit
- **Add new movies** with full details (auto-assigned to the logged-in production company)
- **Search movies** by title, release year, or running time
- **Sort movies** by most recent, highest profit, highest revenue, longest running time, or highest budget

### Networking
- **Multi-client TCP server** handling concurrent connections via multithreading
- **Real-time movie transfer** between production companies over the network
- **Live updates** - when a movie is transferred to your company, it appears in your table immediately without refreshing
- **Client identification** - each client registers with the server using their company name

### Data Persistence
- Movie data loaded from a CSV-style text file (`movies.txt`) at server startup
- File-based data recovery via the undo/reload feature

## Architecture

```
┌─────────────────┐         TCP Socket (Port 3000)         ┌─────────────────┐
│   JavaFX Client  │ ◄──────────────────────────────────► │    Java Server   │
│  (Production Co.) │         DataWrapper Protocol          │  (Multi-threaded)│
├─────────────────┤                                        ├─────────────────┤
│ StartMenu       │    Commands:                           │ Movie Database   │
│ MovieList Table  │    • "Name"     → Register client     │ Client Registry  │
│ Add Movie Form   │    • "Login"    → Get company movies  │ Password Map     │
│ Transfer Panel   │    • "Add"      → Add new movie       │ Socket Routing   │
│ Search & Sort    │    • "Transfer" → Transfer movie      │                  │
└─────────────────┘                                        └─────────────────┘
```

## Tech Stack

| Component | Technology |
|-----------|-----------|
| **Language** | Java |
| **GUI Framework** | JavaFX (FXML-based UI) |
| **Networking** | Java TCP Sockets (`ServerSocket`, `Socket`) |
| **Serialization** | Java Object Serialization (`ObjectInputStream`/`ObjectOutputStream`) |
| **Concurrency** | Java Threads (multi-client server, async client reads) |
| **Build** | Maven/IntelliJ project structure |

## Project Structure

```
IMDB-Movie-Database/
├── MyApplication.java              # JavaFX application entry point
├── model/
│   ├── Movie.java                  # Movie entity (Serializable)
│   ├── DataWrapper.java            # Network protocol wrapper (command + data)
│   └── TransferData.java           # Transfer payload (sender, receiver, movie)
├── network/
│   ├── Server.java                 # Multi-threaded TCP server
│   └── SocketWrapper.java          # Socket I/O abstraction layer
├── controller/
│   ├── StartMenuController.java    # Login screen (company name input)
│   ├── MovieListController.java    # Main dashboard (table, search, sort)
│   ├── AddMovieController.java     # Add movie form
│   └── TransferController.java     # Movie transfer between companies
├── service/
│   ├── TitleFind.java              # Search by movie title
│   ├── ReleaseFind.java            # Search by release year
│   ├── RunningFind.java            # Search by running time
│   ├── MostRecentMovies.java       # Sort by most recent release
│   ├── MostProfitedMovies.java     # Sort by highest profit
│   ├── MostRevenuedMovies.java     # Sort by highest revenue
│   ├── MostRunningTime.java        # Sort by longest running time
│   └── MostBudgetMovies.java       # Sort by highest budget
├── util/
│   └── FileOperations.java         # CSV file parser for movie data
├── .gitignore
└── README.md
```

## How It Works

### Server Side
1. The server reads `movies.txt` and loads the movie database into memory
2. Listens on **port 3000** for incoming client connections
3. Each client connection is handled in a separate thread
4. Maintains a `HashMap<String, SocketWrapper>` to route transfers between clients
5. Processes commands: `Login` (return company movies), `Add` (add to DB), `Transfer` (route to recipient)

### Client Side
1. User enters their **production company name** on the start menu
2. Client connects to the server, registers the company name, and requests its movies
3. Movies are displayed in a JavaFX `TableView` with full details
4. A background listener thread watches for incoming transfers from other companies
5. Users can search, sort, add movies, or transfer movies to other companies

### Network Protocol
Communication uses `DataWrapper` objects sent over `ObjectOutputStream`:

| Command | Payload | Description |
|---------|---------|-------------|
| `Name` | `String` (company name) | Register client identity |
| `Login` | `String` (company name) | Request movies for this company |
| `Add` | `Movie` object | Add a new movie to the database |
| `Transfer` | `TransferData` (sender, movie, receiver) | Transfer movie ownership |

## Getting Started

### Prerequisites
- **Java JDK 17+** (with JavaFX support)
- **JavaFX SDK** (if not bundled with your JDK)

### Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/Musa-Tur-Farazi/Java-Project.git
   ```

2. Place your `movies.txt` data file in the project root. Format:
   ```
   MovieTitle,Year,Genre1,Genre2,Genre3,RunningTime,ProductionCompany,Budget,Revenue
   ```

3. Start the server:
   ```bash
   javac src/main/java/com/example/demo1/network/Server.java
   java com.example.demo1.network.Server
   ```

4. Start one or more clients:
   ```bash
   javac src/main/java/com/example/demo1/MyApplication.java
   java com.example.demo1.MyApplication
   ```

5. Enter a production company name (matching entries in `movies.txt`) and press Enter.

### Using an IDE
- Open the project in **IntelliJ IDEA** or **Eclipse**
- Run `Server.java` first
- Run `MyApplication.java` for each client instance
- Use different production company names for each client to test transfers

## Authors

- **Musa Tur Farazi** (Student ID: 2005038)

CSE, Bangladesh University of Engineering and Technology (BUET)

*Object-Oriented Programming (CSE 108) Course Project*

## License

This project was developed as an academic course project at BUET.
