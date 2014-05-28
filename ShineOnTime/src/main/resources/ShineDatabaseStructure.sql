CREATE TABLE Reservation (
  reservation_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  start TIMESTAMP(3) NOT NULL,
  duration NUMBER(9) NOT NULL,
  email VARCHAR2(50) NOT NULL,
  
  CONSTRAINT chk_email CHECK (email LIKE '%@%.%')
);