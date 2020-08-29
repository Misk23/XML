import { Chapter } from './chapter';
import { Citation } from './citation';

export interface ScientificPublicationEditDTO {
    readonly id: string;
    title: string;
    keywords: string[];
    chapters: Chapter[];
    references: Citation[];
}
