import React, { useEffect, useState } from "react";
import CakesList from './components/CakesList';
import CakeForm from './components/CakeForm';

function App() {
  const [cakes, setCakes] = useState([])

  const addCake = (cake) => {
    let updatedCakes = [...cakes,cake];
    setCakes(updatedCakes);
  }

  useEffect(() => {
    fetchData();
  }, [cakes.length]);

  const fetchData = (() => {
    console.log("Fetching cakes from spring boot app");
    fetch("http://localhost:8080/cakes")
        .then(
            res => res.json()
        )
        .then(
            parsedJSON => setCakes(parsedJSON)
        )

  })

  return (
        <div style={{display:"flex", flexDirection:"row"}}>
            <CakeForm addCake={addCake} />
            <CakesList cakes={cakes} />
        </div>
  );
}

export default App;
