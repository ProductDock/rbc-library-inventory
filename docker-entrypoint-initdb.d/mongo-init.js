db.createUser(
    {
        user: "root1",
        pwd: "root1",
        roles:[
        {
            role :"readWrite",
            db: "inventory-db"
        }]
    }
);
db.bookshelf.insert([
  { bookId: "1", bookCopies: 1, reservedBooks: 0, rentedBooks: 0 },
  { bookId: "2", bookCopies: 1, reservedBooks: 0, rentedBooks: 1 },
  { bookId: "3", bookCopies: 1, reservedBooks: 0, rentedBooks: 0 },
  { bookId: "4", bookCopies: 1, reservedBooks: 0, rentedBooks: 1 },
]);