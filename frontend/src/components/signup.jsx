import { useState } from "react";
import { Container, Form } from "react-bootstrap";
import { getEmployeeByUsername, handleLogin, handleRegister } from "../api/auth";
import { useNavigate } from "react-router-dom";

export default function SignUpPage(props) {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("");

    const navigate = useNavigate();

    const handleCheck = () => {
        if (role === "") {
            setRole("Manager");
        } else {
            setRole("");
        }
    }

    const handleSubmit = async (e) => {
        e.preventDefault();

        const user = {
            firstName: firstName,
            lastName: lastName,
            username: username,
            password: password,
            role: role
        };

        console.log(user);

        const regResponse = await handleRegister(user);

        console.log(regResponse);

        const loginResponse = await handleLogin(user);

        console.log(loginResponse);

        sessionStorage.setItem("token", loginResponse.data.accessToken);
        sessionStorage.setItem("username", username);

        const userData = await getEmployeeByUsername(username);

        navigate(`/home/${userData.data.role.name.toLowerCase()}`);
    }

    return (
        <Container className="pageContainer">
            <h1>Signup</h1>
            <Form onSubmit={handleSubmit} className="loginForm">
            <Form.Group className="mb-3" controlId="firstName">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control onChange={(e) => setFirstName(e.target.value)} type="text" placeholder="Enter first name" />
                </Form.Group><Form.Group className="mb-3" controlId="username">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control onChange={(e) => setLastName(e.target.value)} type="text" placeholder="Enter last name" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="username">
                    <Form.Label>Username</Form.Label>
                    <Form.Control onChange={(e) => setUsername(e.target.value)} type="text" placeholder="Enter username" />
                </Form.Group>
                <Form.Group className="mb-3" controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control onChange={(e) => setPassword(e.target.value)} type="password" placeholder="Enter password" />
                    <span></span>
                </Form.Group>
                <Form.Group className="mb-3" controlId="role">
                    <Form.Label>Manager?</Form.Label>
                    <Form.Check onClick={handleCheck} />
                </Form.Group>
                <button className="rsBtn" type="submit">Signup</button>
                <button onClick={props.switch} className="mx-3 rsBtn" type="submit">Login</button>
            </Form>
        </Container>
    )
}