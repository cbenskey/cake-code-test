import React from 'react';

function CakesList(props) {
  let reversedCakes = [...props.cakes].reverse();

  return (
      <div style={{flexFlow: "column"}}>
        <center><h2>Cakes List</h2></center>
        {reversedCakes.map((cake, index) => (
            <div className="card" key={index}>
              <div className="card-body">
                <h5 className="card-title">{ cake.title }</h5>
                <h6 className="card-subtitle mb-2 text-muted">{ cake.desc }</h6>
                <img src={ cake.image } alt={ cake.title }/>
              </div>
            </div>
        ))}
      </div>
  )
}

export default CakesList;