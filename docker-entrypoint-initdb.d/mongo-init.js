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