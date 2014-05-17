DROP table users;

CREATE TABLE Reservation (
  reservation_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  reservation_start DATETIME,
  reservation_end DATETIME NOT NULL,
  email VARCHAR(50) NOT NULL,
  CONSTRAINT chk_email CHECK (email LIKE '%@%.%')
);