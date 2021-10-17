import React, { useState } from 'react';

function CakeForm({ addCake }) {
  const [title, setTitle] = useState("");
  const [desc, setDesc] = useState("");
  const [image, setImage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    let cake = {"title":title,
      "desc":desc,
      "image": image};


    fetch('http://localhost:8080/cakes', {
      method: 'POST',
      body: JSON.stringify(cake) ,
      headers: { 'Content-Type': 'application/json' },
    })
        .then(res => res.json())
        .then(json => addCake(cake))

  }

  return (
      <div style={{flexBasis:"50%", padding:"10px"}}>
      <form onSubmit={e => {handleSubmit(e)}}>
        <center><h2>Add Cake</h2></center>
        <div className="form-group">
          <label>Title</label>
          <input type="text" name="title" className="form-control" placeholder="Title" value={title} onChange={e => setTitle(e.target.value)}/>
          <small className="form-text text-muted">A short name for the cake</small>
        </div>
        <div className="form-group">
          <label>Description</label>
          <input type="text" name="desc" className="form-control" placeholder="Description" value={desc} onChange={e => setDesc(e.target.value)}/>
          <small className="form-text text-muted">A longer description of the cake</small>
        </div>
        <div className="form-group">
          <label>Image Url</label>
          <input type="text" name="image" className="form-control" placeholder="Image Url" value={image} onChange={e => setImage(e.target.value)}/>
          <small className="form-text text-muted">A url of an image of the cake</small>
        </div>
        <button type="submit" className="btn btn-primary">Save Cake</button>
      </form>
      </div>
  )
}

export default CakeForm;