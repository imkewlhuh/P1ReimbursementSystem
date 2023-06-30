let apiURL = '';

if (process.env.NODE_ENV === 'development') {
    apiURL = 'http://localhost:8080';
} else {
    apiURL = "PRODUCTION LINK GOES HERE";
}

export const baseURL = apiURL;