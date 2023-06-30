import { Container } from "react-bootstrap";
import LoginPage from "../components/login";
import { useState } from "react";
import SignUpPage from "../components/signup";

export default function Auth() {
    const [showLogin, setShowLogin] = useState(true);

    return (
        <>
            {
                showLogin ?
                    <LoginPage switch={() => setShowLogin(!showLogin)} />
                    : <SignUpPage switch={() => setShowLogin(!showLogin)} />
            }
        </>
    )
};