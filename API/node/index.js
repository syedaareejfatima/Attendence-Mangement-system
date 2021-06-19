let express = require('express');
let app = express();
let courseRoute = require('./course');
let bodyParser = require('body-parser');

app.use(bodyParser.json())
app.use((req, res, next)=>{
    console.log(`${new Date().toString()} => ${req.originalUrl}`, req.body)
    next()
});
app.use(courseRoute);   
app.use(express.static('public'));
const PORT = 3000
app.listen(PORT, ()=> {console.info(`Server is listening on ${PORT}`)});