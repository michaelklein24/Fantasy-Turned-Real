import { AuthControllerApi } from "./api/api";
import { Configuration } from "./api/configuration";

const config = new Configuration({
    basePath: 'http://localhost:8080'
})

const apiClient = {
    auth: new AuthControllerApi(config)
}

export default apiClient;
export * from './api/index';