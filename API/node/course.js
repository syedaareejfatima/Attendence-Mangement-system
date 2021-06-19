let CourseModel = require('./models/course_model')
let express = require('express')
let router = express.Router()


router.post('/course',(req, res) =>{
    if(!req.body) return res.status(400).send('Request body is missing')
    let model = new CourseModel(req.body)
    model.save().then(doc =>{
        if(!doc || doc.length === 0) return res.status(500).send(doc) 
        res.status(201).send(doc)
    }).catch(err =>{
        res.status(500).json(err)
    })
})

// course?id=A4 //&&
router.get('/course', (req, res) => {
    if(req.query.id) res.send(`You have requested for Course ${req.query.id}`)
    else res.send("You have requested a Course")
});

//course/id/name
router.get('/course/:id/:name', (req, res) => {
    res.send(`You have requested for Course ${req.params.name} with ID: ${req.params.id}`)
});

module.exports = router