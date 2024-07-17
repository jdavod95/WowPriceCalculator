import { Quality } from "./quality";

export interface Resource {
    id?: number;
    name: string;
    onStock?: number;
    quality?: Quality;
}