export interface Citation {
    id: string;
    authorNames: string[];
    year: number;
    publicationTitle: string;
    text?: string;
    url: string;
}
