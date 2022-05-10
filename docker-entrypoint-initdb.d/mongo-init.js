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
  { _id: "1", bookId: "1", bookCopies: 1, reservedBooks: 0, rentedBooks: 0 },
  { _id: "2", bookId: "2", bookCopies: 1, reservedBooks: 0, rentedBooks: 1 },
  { _id: "3", bookId: "3", bookCopies: 1, reservedBooks: 0, rentedBooks: 0 },
  { _id: "4", bookId: "4", bookCopies: 1, reservedBooks: 0, rentedBooks: 1 },
]);