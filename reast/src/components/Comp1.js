import React, { useEffect, useState } from 'react'

export default function Comp1() {
    var [api, setApi] = useState([]);
    useEffect(() => {
        fetch("http://localhost:5005/user_api")
            .then((res) => res.json())
            .then((value) => {
                setApi(value);
            })
    }, [])
    return (
        <>
            <h1>Test</h1>
            {api.length > 0 && api.map(obj => (
                <li key={obj.email}> {obj.name}, {obj.email}</li>
            ))}
        </>
    )
}
