// import UserProfile from "./UserProfile.jsx";
// import {useState, useEffect} from 'react'
//
// const users = [
//     {
//         name: "Jamila",
//         age: 22,
//         gender: "FEMALE"
//     },
//     {
//         name: "Ana",
//         age: 45,
//         gender: "FEMALE"
//     },
//     {
//         name: "Alex",
//         age: 18,
//         gender: "MALE"
//     },
//     {
//         name: "Bilal",
//         age: 27,
//         gender: "MALE"
//     }
// ]
//
//
// const UserProfiles = ({users})=> (
//     <div>
//         {users.map((user, index) =>(
//             <UserProfile
//                 key={index}
//                 name={user.name}
//                 age={user.age}
//                 gender={user.gender}
//                 imageNumber={index}
//             />
//         ))}
//     </div>
// )
//
// function App() {
//
//     const [counter, setCounter] = useState(0);
//
//     const [isLoading, setIsLoading] = useState(false);
//
//     useEffect(() => {
//         setIsLoading(true)
//         setTimeout(() => {
//             setIsLoading(false)
//         }, 4000)
//     }, [])
//
//     if(isLoading){
//         return "loading ...";
//     }
//
//     return (
//         <div>
//             <button onClick={() => setCounter(prevCounter => prevCounter+1)}>
//                 Increment Counter
//             </button>
//             <h1>{counter}</h1>
//             <UserProfiles users={users}/>
//         </div>
//     )
// }
//
// export default App





import { Button } from '@chakra-ui/react';
import SidebarWithHeader from "./shared/SideBar.jsx";

const App = () => {
    return (
        <SidebarWithHeader>
            <Button colorScheme='teal' variant='outline'>Click me</Button>
        </SidebarWithHeader>
    )
}

export default App

